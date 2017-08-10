
FROM tomcat:latest
MAINTAINER RunpingHuang

ENV TOMCAT_HOME /usr/local/tomcat

COPY . /code/ProjectFarm
WORKDIR /code/ProjectFarm

COPY ./target/ProjectFarm.war $TOMCAT_HOME/webapps

COPY ./tomcat-user.xml $TOMCAT_HOME/conf/

