#!/bin/bash

cat localhost_access_log.*.txt|awk '{print $1}' |sort|uniq -c|sort -rn|head -10

#或者 cat localhost_access_log.*.txt|cut -d- -f1|sort|uniq -c|sort -rn|head -10

