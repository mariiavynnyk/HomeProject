FROM maven:3.6.3-adoptopenjdk-8

USER root

RUN apt-get update && apt-get install entr -y && apt-get install -yq libgconf-2-4 libnss3-dev libx11-dev

COPY . /app

WORKDIR app

ENTRYPOINT ["mvn"]