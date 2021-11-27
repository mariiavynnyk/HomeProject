#!/bin/bash

set -e
EXIT_CODE=0

docker container stop selenoid-ui selenoid || EXIT_CODE=$?
docker container rm selenoid-ui selenoid || EXIT_CODE=$?
docker container stop $(docker ps -a --format='{{ .ID }}' -f name=maven) || EXIT_CODE=$?
docker container rm $(docker ps -a --format='{{ .ID }}' -f name=maven) || EXIT_CODE=$?
docker rmi $(docker images -q selenoid/vnc) || EXIT_CODE=$?
docker rmi $(docker images -q aerokube/selenoid) || EXIT_CODE=$?
docker rmi $(docker images -q aerokube/selenoid-ui) || EXIT_CODE=$?
docker rmi $(docker images -q maven) || EXIT_CODE=$?
#docker rmi maven:3.6.3-adoptopenjdk-8 || EXIT_CODE=$?
docker rmi maven:$(docker images --format='{{.Tag}}' maven) || EXIT_CODE=$?
docker network rm selenoid || EXIT_CODE=$?

echo $EXIT_CODE