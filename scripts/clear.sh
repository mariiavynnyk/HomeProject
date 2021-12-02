#!/bin/bash

set -e
EXIT_CODE=0

docker container stop selenoid-ui selenoid || EXIT_CODE=$?
docker container rm selenoid-ui selenoid || EXIT_CODE=$?
docker container stop $(docker ps -a --format='{{ .ID }}' -f name=maven) || EXIT_CODE=$?
docker container rm $(docker ps -a --format='{{ .ID }}' -f name=maven) || EXIT_CODE=$?
docker network rm selenoid || EXIT_CODE=$?

echo $EXIT_CODE