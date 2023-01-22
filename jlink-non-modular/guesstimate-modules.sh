#!/usr/bin/env bash
# This script makes a guess about the JPMS modules required by the non-modularized components of our program.
#
# This uses the 'jdeps' tool to inspect the program distribution. Specifically, jdeps does static analysis on the
# program .jar file and the dependency .jar files. For example, if it finds an import in the code for
# "java.util.logging.Logger" then jdeps knows that the program depends on the "java.logging" module. But, this is only
# an incomplete guess, rather a "guesstimate" :). jlink cannot detect which classes are used via reflection. This is a
# critical concept.
#
# By contrast, for the modularized components of the program we can find the exact module dependencies. For example, the
# ClassGraph library dependency is actually modularized. It depends on "java.xml", "jdk.unsupported", "java.management",
# and "java.logging". We find this just by looking at the dependency declarations in its "module-info.java" file: https://github.com/classgraph/classgraph/blob/8b358c63db2b4a5dc844f96e9453809b8f83adf8/src/module-info/io.github.classgraph/module-info.java
# The 'list-explicit-modules.sh' script is an accompaniment script to this one and it does exactly that.
#
# The more the program is modularized, the less guesswork about its dependencies!

set -eu

DISTRIBUTION=build/install/jlink-non-modular

if [[ ! -d "$DISTRIBUTION" ]]; then
  echo >&2 "The program distribution must first be built. Exiting."
  exit 1
fi

echo "Guesstimating the Java modules that the non-modularized components depends on..."

# We need to use the "--multi-release" option because of the ClassGraph dependency is a "multi-release" jar (I think...
# I don't really get it)
jdeps --multi-release 17 --print-module-deps "$DISTRIBUTION/lib/"*.jar
