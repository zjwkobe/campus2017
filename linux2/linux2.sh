#!/bin/bash
read -p "Please input the source file you want to send (full path): " src
read -p "Please input the address of the destination host : " host
read -p "Please input the destination file path : " des
if [ ! -f $src ]
then
	echo "Error : Target file not exist!"
else
	scp $src root@$host:$des
fi
