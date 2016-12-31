#!/bin/bash

function usage() {
    echo "usage: Top10Ip.sh <LogFile>"
}

if [ $# -ne 1 ];then
    usage
    exit 1
fi

echo "  Times IP"
grep '\([0-9]\{1,3\}[.]\)\{3\}[0-9]\{1,3\}' $1 |sort |uniq -c |sort -r |head -n 10

