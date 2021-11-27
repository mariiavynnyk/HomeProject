#!/bin/bash

docker network create selenoid --subnet 172.37.40.0/24
docker pull selenoid/vnc:chrome_91.0
docker build -t maven . -f image/maven-ci/Dockerfile