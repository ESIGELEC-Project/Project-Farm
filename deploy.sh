#!/bin/sh
TOMCAT_HOME=/usr/local/tomcat

cp ./target/ProjectFarm.war $TOMCAT_HOME/webapps

cp -u ./tomcat-user.xml $TOMCAT_HOME/conf/

/bin/sh $TOMCAT_HOME/bin/start-tomcat.sh