1. > docker network create selenoid

2. > docker pull selenoid/vnc:chrome_91.0

3. > docker run -d --net selenoid --name selenoid -p 4445:4444 -v /var/run/docker.sock:/var/run/docker.sock \
   -v $PWD:/etc/selenoid/:ro aerokube/selenoid:latest-release \
   -conf /etc/selenoid/browsers.json -video-output-dir /opt/selenoid/video/ -timeout 3m0s -container-network selenoid

4. > docker run -d --net selenoid --name selenoid-ui -p 8081:8080 aerokube/selenoid-ui:latest-release \
   --selenoid-uri http://selenoid:4444

5. > docker build -t maven . -f image/maven-ci/Dockerfile

6. > docker run --rm --net selenoid --name maven maven test -e -X

7. > docker run --rm --name maven -v $PWD/allure-report:/app/allure-report/ maven site
