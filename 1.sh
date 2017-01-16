#!/bin/bash

echo "begin..."

if [  ! -n "$1"  ]  ;then
	echo "no file found "
else
	str=$1/*access_log*
	cat localhost_access_log.* | awk '{print $1}' | sort | uniq -c | sort -nr | head -10 > top10.log
	echo "read top10.log ? (y or n)"
	read ques
	if [ "$ques" == "Y" -o "$ques" == "y" ] ; then
		cat top10.log
	fi
fi

echo "end..."


