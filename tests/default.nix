{ nixpkgs ? import <nixpkgs> {} }:

let
  inherit (nixpkgs) pkgs;
  inherit (pkgs) haskellPackages;

  haskellDeps = ps: with ps; [
    base
    parsec
    shake
    strip-ansi-escape
    regex-base
    regex-tdfa
  ];

  ghc = haskellPackages.ghcWithPackages haskellDeps;
in

pkgs.stdenv.mkDerivation {
  name = "java-test-runner";
  buildInputs = with pkgs; [ ghc makeWrapper ];

  src = ./runner.hs;

  unpackPhase = "true";
  installPhase = ''
    mkdir -p $out/bin/
    cp ${./runner.hs} $out/runner.hs
    makeWrapper ${ghc}/bin/runhaskell $out/bin/java-test-runner --add-flags $out/runner.hs
  '';
}
