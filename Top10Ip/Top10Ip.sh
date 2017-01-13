#!/bin/bash

read -p "input path of access logs :	" path

if [ -f temp_log ]
then
	rm -rf temp_log
fi

for file in $path / *access_log*
do
	if [ -f $file ]
	then
		awk '{print $1}' $file >> temp_log
	fi
done

cat temp_log | sort | uniq -c | sort -n -r | head -n 10

rm -rf temp_log