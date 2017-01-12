#!/bin/bash
###scp /绝对路径/news.txt 服务器用户名@IP:/远程服务器上的路径
###需加-r 参数，否则报错not a regular file
echo -e "test">>test;

scp -r /home/yas/Desktop/text.sh yas@192.168.181.130:/ /home/yas/
