#!/bin/bash
#统计日志文件中访问次数前10的IP及相应次数
ip=$(cat $a | grep -E -o "([0-9]{1,3}[\.]){3}[0-9]{1,3}" |sort|uniq -c|sort -k 1 -n -r|
		awk '{print NR"\t"$b"\t"$a}') 
if [ $# -eq 2 ];then
    echo -e "序号\tip\t\t次数">$b
    echo "$ip">>$b
  else 
    echo -e "序号\tip\t\t次数"      
    echo "$ip"
fi
