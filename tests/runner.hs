{-# LANGUAGE RecordWildCards #-}
import System.IO
import System.Exit
import System.Environment
import Control.Monad

import Development.Shake hiding ((*>))
import Development.Shake.Command
import Development.Shake.FilePath
import Development.Shake.Util

import Text.Parsec hiding (parseTest)
import Text.Parsec.Char
import Text.Regex.Base
import Text.Regex.TDFA

import Data.Text (Text, pack, unpack, strip)
import Data.String.AnsiEscapeCodes.Strip.Text

type TestPath = FilePath

build = "_build"

data Expect = ExpectOK | ExpectFail String deriving Show

data Test = Test
  { name   :: String
  , file   :: String
  , scalac :: Expect
  , statix :: Expect
  } deriving (Show)

-- entrypoint

main = do
  [scriptDir, testFile] <- getArgs
  rules scriptDir testFile

-- Parser for test files

parseExpectation :: Parsec Text u Expect
parseExpectation =
      (try $ spaces >> string "ok" <* newline >> return ExpectOK)
  <|> (spaces >> ExpectFail <$> (string "fail" *> manyTill anyChar newline))

parseTest :: String -> Parsec Text u Test
parseTest name = do
  stxExp  <- string "STATIX" >> parseExpectation
  scaExp  <- string "SCALAC" >> parseExpectation

  content <- manyTill anyChar eof

  return $ Test name (stripStr content) scaExp stxExp

  where
    stripStr = unpack . strip . pack

-- auxiliary defs

checkScalaExpectation :: Expect -> ExitCode -> String -> Bool
checkScalaExpectation ExpectOK        code sout = code == ExitSuccess
checkScalaExpectation (ExpectFail er) code sout =
  case code of
    ExitSuccess      -> False
    ExitFailure erno -> sout =~ (".*" <> er <> ".*")

checkStxExpectation :: Expect -> ExitCode -> String -> Bool
checkStxExpectation ExpectOK        code sout = code == ExitSuccess
checkStxExpectation (ExpectFail er) code sout =
  case code of
    ExitSuccess      -> False
    ExitFailure erno -> sout =~ (".*" <> er <> ".*")

resultString :: Bool -> String
resultString ok = if ok then "SUCCESS" else "FAILURE"

-- rules

rules :: FilePath -> TestPath -> IO ()
rules scriptDir testPath = do

  -- some directories
  let specDir     = scriptDir </> ".." </> "src"
  let sunshine    = scriptDir </> ".." </> "bin/org.metaborg.sunshine2-2.5.11.jar"
  let langDir     = scriptDir </> ".." </> "lib/scala.spfx/lang.scala.sdf3"
  let buildDir    = build </> testPath

  -- parse the test file
  txt      <- pack <$> readFile testPath
  Test{..} <- either (\err -> die $ "Couldn't parse test file: " ++ show err) return
    $ parse (parseTest testPath) testPath txt

  -- write the scala file if they've changed
  let scalaFile = (buildDir </> "test" <.> "scala")
  writeFileChanged scalaFile file

  -- some targets
  let aterm     = scalaFile -<.> "aterm"
  let result    = buildDir </> "result"

  shake shakeOptions{ shakeFiles = buildDir, shakeChange = ChangeDigest } $ do

    want [ result ]

    -- auxiliary file targets
    -------------------------

    "//*.sca3" %> \out -> do
      let input = out -<.> "scala"
      need [ input ]
      cmd_ "cp" input out

    "//*.aterm" %> \out -> do
      let sca = out -<.> "sca3"
      need [ sca ]

      Stdout sout <- cmd "java -jar" sunshine
        "parse -l" langDir "-p . -i" sca

      writeFileChanged out sout

    -- toplevel targets
    -------------------

    result %> \out -> do
      alwaysRerun

      stxRes   <- readFile' $ buildDir </> "stx.result"
      scalaRes <- readFile' $ buildDir </> "scala.result"

      let res = resultString $ stxRes == "SUCCESS" && scalaRes == "SUCCESS"
      writeFile' out res

      liftIO $ putStrLn $ "[" <> res <> "] " <> testPath

    [ buildDir </> "scala.out" , buildDir </> "scala.result" ] &%> \[out, res] -> do
      -- Make sure to depend on the main input
      -- because Shake doesn't support dynamic dependencies
      need [ testPath ]
      need [ scalaFile ]

      -- run scalac on the inputs
      (Exit code, Stdouterr sout) <- withVerbosity Verbose $
                                       cmd "scalac -d" buildDir scalaFile
      writeFileChanged out $ sout

      let result = resultString (checkScalaExpectation scalac code sout)
      writeFile' res result

      liftIO $ putStrLn $ "[SCALA:" <> result <> "] " <> testPath

    [ buildDir </> "stx.out" , buildDir </> "stx.result" ] &%> \[out, res] -> do
      -- Make sure to depend on the main input
      need [ testPath ]
      need [ aterm ]

      -- depend on the entire spec
      getDirectoryFiles specDir ["//*.mstx"] >>= \mf -> do
        need [ specDir </> f | f <- mf ]

      -- run statix on all the aterms
      (Exit code, Stdouterr sout) <- withVerbosity Diagnostic $
                                      cmd "statix check -I" [specDir] "scala" aterm
      let sout' = unpack $ stripAnsiEscapeCodes (pack sout)
      writeFileChanged out $ sout'

      let result = resultString (checkStxExpectation statix code sout')
      writeFile' res result

      liftIO $ putStrLn $ "[STATIX:" <> result <> "] " <> testPath
