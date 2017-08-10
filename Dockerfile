
FROM mysql

ADD ./dump.sql /tmp

RUN \
    mysql  -e 'CREATE DATABASE IF NOT EXISTS ProjectFarm' \
    #echo 123 | mysql -u root -p ProjectFarm < /tmp/dump.sql

