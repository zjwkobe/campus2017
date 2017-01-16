#!/bin/bash

echo "begin..."

srcPath=/dir1
tarPath=/tmp
tarUser=test
tarIp=`dig l-test.dev.cn1 +short | tail -1`

echo $tarIp

if [ ! -n $1 ] ; then
	echo "no file found"
else
	scp $srcPath/$1 $tarUser@$tarIp:$tarPath
fi

echo "end..."
