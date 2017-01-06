#!/bin/bash
#
# build a directory to stores access log file
#
mkdir access
cp localhost_access_log.*  access
#
# clear countIp.txt,use countIp to store all ip adress 
#
cat /dec/null > countIp.txt
#
exec 3>>countIp.txt
#
for file in access/*
do
  if [ -f "$file" ]
  then
   cat $file | cut -d ' ' -f 1 >&3
  fi
done
#
# statics ip and display
#
cat countIp.txt | sort | uniq -c | sort -n -r | head -n 10
