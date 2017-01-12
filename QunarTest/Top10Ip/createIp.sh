#!/bin/bash

###模仿tomcat日志

if [ -f data.log ];then
    rm -rf data.log;
fi

for i in $(seq 1000);do
    r=$RANDOM;
    n=$[$r%200];
    echo -e "127.0.0.$n - - ?">>access.log;
done

