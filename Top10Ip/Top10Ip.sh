#!/bin/bash
read -p "Please input tomcat log folder path : " root

if [ -d $root ]
then
	cd $root

	for file in `ls localhost_access_log.*`
	do
		cat $file>>mylog
	done

	awk '{a[$1]++}END{for(i in a) print a[i]" time(s)   "i}' mylog > countip

	sort -n -r countip | head -10 > result

	rm -rdf countip mylog
else
	echo "Error : Folder don't exist!"
fi
