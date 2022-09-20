#!/bin/bash
pid=$(lsof -i:8081 | grep "^java" | awk '{print $2}')
if [ $pid ]; then
    kill -9 $pid;
else
    echo "no process"
fi

mvn spring-boot:run -Dmaven.test.skip -Drun.jvmArguments="-Xmx384m -XX:PermSize=100m -XX:MaxPermSize=100m"> file.log 2>&1 &
#mvn spring-boot:run -Dmaven.test.skip -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8805 -Xmx384m -XX:PermSize=100m -XX:MaxPermSize=100m"> file.log 2>&1 &
