#!/bin/bash

set -e
EXIT_CODE=0

docker run --rm --net selenoid --name maven -v $PWD/target:/app/target/ dva1986/maven-tests:latest test -e -X || EXIT_CODE=$?

echo $EXIT_CODE