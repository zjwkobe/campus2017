cat localhost_access_log.*.txt|cut -d- -f1|sort|uniq -c|sort -rn|head -10
