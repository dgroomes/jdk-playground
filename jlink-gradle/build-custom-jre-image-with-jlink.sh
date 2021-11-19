#!/usr/bin/env bash
# Build the custom JRE image using 'jlink'

set -eu

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
  --compress=2 \
  --strip-debug \
  --add-modules "$MODULES" \
  --output $CUSTOM_JRE

echo "Successfully built a custom JRE image at: $CUSTOM_JRE"
