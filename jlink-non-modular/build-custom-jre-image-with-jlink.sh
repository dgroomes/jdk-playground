#!/usr/bin/env bash
# Build a custom JRE image using jlink.
#
# This depends on the program distribution already having been built by Gradle using `./gradlew installDist` and that
# the "modules.txt" file has been hand-crafted to list the exact Java modules that the program depends on.

set -eu

DISTRIBUTION=build/install/jlink-non-modular

if [[ ! -d "$DISTRIBUTION" ]]; then
  echo >&2 "The program distribution must first be built. Exiting."
  exit 1
fi

CUSTOM_JRE=build/custom-jre-image

# Delete pre-existing image if it exists
if [[ -f "$CUSTOM_JRE/release" ]]; then
  echo "A custom JRE already exists at $CUSTOM_JRE. Deleting it..."
  rm -rf "$CUSTOM_JRE"
fi

MODULES=$(cat modules.txt)
echo "Building a custom JRE image with modules: $MODULES"

# Build the custom JRE image.
jlink \
  --no-header-files \
  --no-man-pages \
  --compress=zip-6 \
  --strip-debug \
  --add-modules "$MODULES" \
  --output $CUSTOM_JRE

echo "Successfully built a custom JRE image at: $CUSTOM_JRE"
