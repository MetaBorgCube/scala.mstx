# paths
SCALA_FRONT  =  lib/scala.spfx/lang.scala.sdf3/
SPFX_VERSION =  2.5.11
SUNSHINE_URL =  http://artifacts.metaborg.org/service/local/repositories/releases/content/org/metaborg/org.metaborg.sunshine2/$(SPFX_VERSION)/org.metaborg.sunshine2-$(SPFX_VERSION).jar
SUNSHINE_JAR =  bin/org.metaborg.sunshine2-$(SPFX_VERSION).jar
SPEC         =  src/scala.mstx
TESTS        ?= tests/    # directory
TESTRE       ?= '*.scala' # iname

## external commands with configuration
MAVEN_OPTS   = "-Xms512m -Xmx1024m -Xss16m"
MAVEN        = MAVEN_OPTS=$(MAVEN_OPTS) mvn
SUNSHINE     = java -jar $(SUNSHINE_JAR)
PARSE        = $(SUNSHINE) parse -l $(SCALA_FRONT) -p . -i
STATIX       = statix $(SPEC)
SCALAC       = scalac

TEST_SOURCES = $(shell find $(TESTS) -type f -name $(TESTRE))

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

test: $(TEST_SOURCES)
	@./tests/run $(TEST_SOURCES) | grep '[\[]SUCCESS\|FAILURE'

## Building

## Cleaning

test-clean:
	-@rm -rf _build
