# paths
SCALA_FRONT  =  lib/scala.spfx/lang.scala.sdf3/
SPFX_VERSION =  2.5.11
SUNSHINE_URL =  http://artifacts.metaborg.org/service/local/repositories/releases/content/org/metaborg/org.metaborg.sunshine2/$(SPFX_VERSION)/org.metaborg.sunshine2-$(SPFX_VERSION).jar
SUNSHINE_JAR =  bin/org.metaborg.sunshine2-$(SPFX_VERSION).jar
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

TESTS        = $(shell find $(TESTDIR) -name $(TESTRE))

TEST_TARGETS = $(TESTS:%.scala=%.result)

.PHONY: all test
.PRECIOUS: %.aterm

## Default target

all: test

## Get depenencies

bin:
	mkdir -p bin

# get spoofax sunshine
bin/org.metaborg.sunshine2-$(SPFX_VERSION).jar: bin
	curl $(SUNSHINE_URL) -o $(SUNSHINE_JAR)

# ensure that spoofax sunshine is available
sunshine: bin/org.metaborg.sunshine2-$(SPFX_VERSION).jar

# compile the scala frontend
lib/scala.spfx/lang.scala.sdf3/target/lang.scala.sdf3-0.1.0-SNAPSHOT.spoofax-language: $(SCALA_FRONT)
	cd $(SCALA_FRONT) && $(MAVEN) verify

# ensure the java spoofax language frontend is compiled and available
scalafront: lib/scala.spfx/lang.scala.sdf3/target/lang.scala.sdf3-0.1.0-SNAPSHOT.spoofax-language sunshine

scalafront-clean:
	cd $(SCALA_FRONT) && $(MAVEN) clean


## Testing

# Turn a scala file into its aterm representation
# using the Spoofax syntax definition
%.aterm: %.scala
	cp $< $(<:%.scala=%.sca)
	$(PARSE_SCALA) $(<:%.scala=%.sca) > $@
	rm -f $(<:%.scala=%.sca)

%.result: %.scala src/
	@./tests/run $< | tee $@ | grep -i "failure\|success\|panic"

test: $(TEST_TARGETS)
test-results: 
	find . -name '*.result' -exec sh -c "cat {} | grep -i 'failure\|success\|panic'" \;

## Building

## Cleaning

test-clean:
	find . -name "*.class" -exec rm {} \;
	find . -name "*.aterm" -exec rm {} \;
	find . -name "*.result" -exec rm {} \;
	find . -name "*.out" -exec rm {} \;
