#!/usr/bin/env bash
# Build the custom JRE image using 'jlink' and 'jdeps'.
# This will also build the program distribution using `./gradlew installDist`

set -eu
set -x

# Build the distribution
./gradlew installDist

DISTRIBUTION=build/install/jlink-gradle
CUSTOM_JRE=build/custom-jre-image

# Delete pre-existing image if it exists
if [[ -f "$CUSTOM_JRE/release" ]]; then
  echo "A custom JRE already exists at $CUSTOM_JRE. Deleting it..."
  rm -rf "$CUSTOM_JRE"
fi

MODULE_PATH="$DISTRIBUTION/lib"

# Use jdeps to list the Java modules that the program distribution depends on. The output will be usable by jlink.
# We need to use the "--multi-release" option because of the "classgraph" dependency (I don't know why).
MODULES=$(jdeps --multi-release 11 --print-module-deps --module-path "$MODULE_PATH" "$DISTRIBUTION/lib/jlink-gradle.jar")
echo "Building a custom JRE image with modules: $MODULES"

# Build the custom JRE image.
jlink \
  --no-header-files \
  --no-man-pages \
  --compress=2 \
  --strip-debug \
  --module-path "$MODULE_PATH" \
  --add-modules dgroomes \
  --output $CUSTOM_JRE

echo "Successfully built a custom JRE image at: $CUSTOM_JRE"
