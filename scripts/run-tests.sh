#!/bin/bash

set -e
EXIT_CODE=0

docker run --rm --net selenoid --name maven maven test -e -X || EXIT_CODE=$?

echo $EXIT_CODE