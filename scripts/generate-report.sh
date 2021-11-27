#!/bin/bash

docker run --rm --name maven -v $PWD/allure-report:/app/allure-report/ maven site