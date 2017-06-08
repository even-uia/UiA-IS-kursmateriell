#!/bin/bash
JAVA_HOME=/opt/jdk1.8.0_112/
export JAVA_HOME
PATH=$JAVA_HOME/bin:$PATH
export PATH
CATALINA_HOME=/opt/apache-tomee-plume-7.0.2/

case $1 in
start)
/bin/su tomcat $CATALINA_HOME/bin/startup.sh
;;
stop)
/bin/su tomcat $CATALINA_HOME/bin/shutdown.sh
;;
restart)
/bin/su tomcat $CATALINA_HOME/bin/shutdown.sh
/bin/su tomcat $CATALINA_HOME/bin/startup.sh
;;
esac
exit 0
