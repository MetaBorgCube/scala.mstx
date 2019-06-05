# paths
SCALA_FRONT  =  lib/scala.spfx/lang.scala.sdf3/
SUNSHINE_URL =  http://artifacts.metaborg.org/service/local/repositories/releases/content/org/metaborg/org.metaborg.sunshine2/2.5.2/org.metaborg.sunshine2-2.5.2.jar
SUNSHINE_JAR =  bin/org.metaborg.sunshine2-2.5.2.jar
SPEC         =  src/scala.mstx
TESTDIR      ?= tests/   # directory
TESTRE       ?= '*.scala' # iname

## external commands with configuration
MAVEN_OPTS   = "-Xms512m -Xmx1024m -Xss16m"
MAVEN        = MAVEN_OPTS=$(MAVEN_OPTS) mvn
SUNSHINE     = java -jar $(SUNSHINE_JAR)
PARSE        = $(SUNSHINE) parse -l $(SCALA_FRONT) -p . -i 
STATIX       = statix $(SPEC)
SCALAC       = scalac

TESTS        = $(shell find $(TESTDIR) -iname $(TESTRE))

TEST_TARGETS = $(TESTS:%.scala=%.result)

.PHONY: all test
.PRECIOUS: %.aterm

## Default target

all: test

## Get depenencies

bin:
	mkdir -p bin

# get spoofax sunshine
bin/org.metaborg.sunshine2-2.5.2.jar: bin
	curl $(SUNSHINE_URL) -o $(SUNSHINE_JAR)

# ensure that spoofax sunshine is available
sunshine: bin/org.metaborg.sunshine2-2.5.2.jar

# compile the scala frontend
lib/scala.spfx/lang.scala.sdf3/target/lang.scala.sdf3-0.1.0-SNAPSHOT.spoofax-language: $(SCALA_FRONT)
	cd $(SCALA_FRONT) && $(MAVEN) verify

# ensure the java spoofax language frontend is compiled and available
scalafront: lib/scala.spfx/lang.scala.sdf3/target/lang.scala.sdf3-0.1.0-SNAPSHOT.spoofax-language sunshine

## Testing

# Turn a scala file into its aterm representation
# using the Spoofax syntax definition
%.aterm: %.scala
	cp $< $(<:%.scala=%.sca)
	$(PARSE_SCALA) $(<:%.scala=%.sca) > $@
	rm -f $(<:%.scala=%.sca)

%.result: %.scala src/
	@./tests/run $< | tee $@ | grep -i "failure\|success"

test: $(TEST_TARGETS)
test-results: 
	find . -iname '*.result' -exec sh -c "cat {} | grep -i 'failure\|success'" \;

## Building

## Cleaning

test-clean:
	find -iname "*.class" -exec rm {} \;
	find -iname "*.aterm" -exec rm {} \;
	find -iname "*.result" -exec rm {} \;
	find -iname "*.out" -exec rm {} \;
