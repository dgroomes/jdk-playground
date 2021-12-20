#!/usr/bin/env bash
# List the modules explicitly required by this program's modularized library dependencies.
#
# This script accompanies the 'guesstimate-modules.sh' script. While the 'guesstimate-modules.sh' script is used for
# making an educated guess about the modules required by non-modularized jars, the 'list-explicit-modules.sh' script
# is for listing modules that are declared by modularized jar. There is no guesswork!
#
# I feel like this script should not be necessary, and that jdeps should be able to do this. But I can't figure out the
# right combinations of options and arguments to get jdeps to do it. It seems like jdeps is designed for examining only
# non-modularized jar.

set -eu

DISTRIBUTION=build/install/jlink-non-modular

if [[ ! -d "$DISTRIBUTION" ]]; then
  echo >&2 "The program distribution must first be built. Exiting."
  exit 1
fi

# This is a naive implementation that iterates over all the jar files in the distribution directory. When a
# non-modularized jar is visited, the "jar --describe-module" command will output "No module descriptor found. Derived automatic module."
# Ignore it.
for jarfile in "$DISTRIBUTION/lib/"*; do
  jar --file="$jarfile" --describe-module --release 17
done
