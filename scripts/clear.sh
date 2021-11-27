#!/bin/bash

docker container stop selenoid-ui selenoid
docker container rm selenoid-ui selenoid
docker rmi $(docker images -q selenoid/vnc)
docker rmi $(docker images -q aerokube/selenoid)
docker rmi $(docker images -q aerokube/selenoid-ui)
docker rmi $(docker images -q maven)
docker network rm selenoid