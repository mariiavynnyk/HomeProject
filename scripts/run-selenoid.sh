#!/bin/bash

docker run -d --net selenoid --name selenoid -p 4445:4444 -v /var/run/docker.sock:/var/run/docker.sock dva1986/selenoid -conf /etc/selenoid/browsers.json -video-output-dir /opt/selenoid/video/ -timeout 3m0s -container-network selenoid
docker run -d --net selenoid --name selenoid-ui -p 8081:8080 aerokube/selenoid-ui:latest-release --selenoid-uri http://selenoid:4444