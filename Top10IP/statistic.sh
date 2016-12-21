#!/bin/bash
#statistic every IP address
#IP address appears in the first segment of the catlog file!!!

#get the catlog fiename
catlog="$1"

cat "$catlog" | cut -d ' ' -f 1 > onlyIP.txt
cat onlyIP.txt | sort -u > uniq.txt

for ip in `cat uniq.txt`
do
	times=`grep -c "$ip" onlyIP.txt`
	echo -e "$times\t $ip" >> temp.txt
done

cat temp.txt | sort -k 1 -n -r >total.txt
cat total.txt | head -n 10 > result.txt


rm -f onlyIP.txt
rm -f uniq.txt
rm -f temp.txt
rm -f total.txt
