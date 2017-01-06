#!/bin/bash
#copy file from local to destination
#


if scp dir1/$1 l-test.dev.cn1:/temp
  then
   echo "copy finished"
else 
   echo "copy failed"
fi