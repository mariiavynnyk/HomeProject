#!/bin/bash

docker network create selenoid --subnet 172.37.40.0/24

docker build . -f image/selenoid/Dockerfile -t dva1986/selenoid
docker build -t maven-tests . -f image/maven-ci/Dockerfile
docker tag maven-tests dva1986/maven-tests:latest