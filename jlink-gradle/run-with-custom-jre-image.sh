#!/usr/bin/env bash
# Run the program using the custom JRE image built with jlink instead of a full distribution of the JRE.
#
# This script requires:
#   * the program distribution to have been built already
#   * the custom JRE to have been built already

if [[ ! -d build/install/jlink-gradle ]]; then
  echo >&2 "The program distribution must be built. Exiting."
  exit 1
fi

if [[ ! -d build/custom-jre-image ]]; then
  echo >&2 "The custom JRE image must be built. Exiting."
  exit 1
fi

export JAVA_HOME="build/custom-jre-image"

build/install/jlink-gradle/bin/jlink-gradle