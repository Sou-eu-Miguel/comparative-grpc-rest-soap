#!/bin/bash

gradle clean build -b ./rest/client-rest/build.gradle.kts
gradle clean build -b ./rest/server-rest/build.gradle.kts

gradle clean build -b ./soap/server-soap/build.gradle
mvn clean install -f ./soap/client-soap/pom.xml

gradle clean build -b ./grpc/client-grpc/build.gradle
gradle clean build -b ./grpc/server-grpc/build.gradle

docker network create "rest-format"
echo "Created rest-format"

echo "Loading compose..."
docker-compose -f ./docker-compose.yml up -d --build --force-recreate --remove-orphans
