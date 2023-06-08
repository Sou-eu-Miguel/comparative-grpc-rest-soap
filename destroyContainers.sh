#!/bin/bash

docker-compose down -v
echo "Down containers"
docker network rm $(docker network ls | grep "rest-format" | awk '// { print $1 }')
echo "Deleted rest-format"
