#######################################################
# File Name: linux2.sh
# Author: liww
# email: 466515927@qq.com
# Created Time: 2017年01月05日 星期四 22时31分16秒
#=========================================================
#!/bin/bash
read -t 5 -p "请输入上传文件路径：" file
read -t 5 -p "请输入用户名：" username

scp  $file $username@l-test.dev.cn1:/tmp 
