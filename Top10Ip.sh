#######################################################
# File Name: Top10Ip.sh
# Author: liww
# email: 466515927@qq.com
# Created Time: 2016年12月29日 星期四 09时22分30秒
#=========================================================
#!/bin/bash
access_path='/etc/httpd/logs/access_log'
if [ -s $access_path ]
   then
      cat $access_path |awk '{print $1}' |sort | uniq -c |sort -k1,1rn|head -10
else
      echo -e  '\e[0;31m' "无访问记录" '\e[0;0m'
fi
