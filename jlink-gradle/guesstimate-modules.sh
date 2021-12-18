#!/usr/bin/env bash
# This script makes a guess about the JPMS modules required by our program.
#
# This uses the `jdeps` tool to inspect the program distribution (the program .jar file and dependency .jar files). This is
# only an incomplete guess, rather a "guesstimate" :) for two reasons. 1) jlink cannot detect the exact classes that might
# be used at runtime because the program might use Java's metra-programming features like the Reflection APIs. And 2)
# the program is not modularized (JPMS). A non-modular program doesn't declare the modules that it needs whereas a
# modular program does declare the modules it needs via the module-info.java (or, module-info.class) file. Consider
# modularizing your project so that you don't have to "guesstimate" the modules it needs.

set -eu

DISTRIBUTION=build/install/jlink-gradle

if [[ ! -d "$DISTRIBUTION" ]]; then
  echo >&2 "The program distribution must first be built. Exiting."
  exit 1
fi

echo "Guesstimating the Java modules that the program depends on..."
# Use jdeps to "guesstimate" the Java modules that the program distribution depends on.
# We need to use the "--multi-release" option because of the "classgraph" dependency (I don't know why).
jdeps --multi-release 17 --print-module-deps "$DISTRIBUTION/lib/"*.jar
