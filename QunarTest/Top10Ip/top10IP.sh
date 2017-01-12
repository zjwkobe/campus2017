#!/bin/bash

###查看本地tomcat log日志，"- -"为ip的分割符

if [ -f access.log ];then
    cat access.log | awk -F "- -" '{print $1}' | sort -t . | uniq -c | sort -n -r | head -10
fi



