#!/usr/bin/env bash
# Run the program using the custom JRE image built with jlink instead of a full distribution of the JRE.
# This custom image even includes the application code!

if [[ ! -d build/custom-jre-image ]]; then
  echo >&2 "The custom JRE image must be built. Exiting."
  exit 1
fi

build/custom-jre-image/bin/java --module dgroomes/dgroomes.App
