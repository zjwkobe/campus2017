#!/bin/bash
#获取输入的文件，取出ip地址，去重并计数，根据访问数从大到小排序，格式化输出
cat $1 | cut -d ' ' -f 1 | uniq -c | sort -k 1 -n -r | awk '{print NR " " $2 " " $1}'