#!/bin/sh
# this script is going to start a single service at a time
docker run --name mysql -v $(pwd)/data:/data -e MYSQL_ROOT_PASSWORD=123 --publish 6603:3306 mysql