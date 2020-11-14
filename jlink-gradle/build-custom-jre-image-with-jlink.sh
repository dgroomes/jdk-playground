#!/usr/bin/env bash
# Build the custom JRE image using 'jlink' and 'jdeps'.
# This depends on the program distribution already having been built by Gradle using `./gradlew installDist`

set -eu

DISTRIBUTION=build/install/jlink-gradle

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

# Use jdeps to list the Java modules that the program distribution depends on. The output will be usable by jlink.
MODULES=$(jdeps --print-module-deps "$DISTRIBUTION/lib/"*.jar)
echo "Building a custom JRE image with modules: $MODULES"

# Build the custom JRE image.
jlink \
  --no-header-files \
  --no-man-pages \
  --compress=2 \
  --strip-debug \
  --add-modules "$MODULES" \
  --output $CUSTOM_JRE

echo "Successfully built a custom JRE image at: $CUSTOM_JRE"