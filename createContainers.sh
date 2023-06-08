#!/bin/bash

gradle clean build -b ./rest/client-rest/build.gradle.kts
gradle clean build -b ./rest/server-rest/build.gradle.kts

docker network create "rest-format"
echo "Created rest-format"

echo "Loading compose..."
docker-compose -f ./docker-compose.yml up -d --build --force-recreate --remove-orphans
