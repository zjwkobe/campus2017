#!/bin/bash

# @Copyright bitingwind 2016/12/6. Email: zhang_sx2013@126.com 

# A qunar.com examination: get the most 10 accessed ip in all access log file of tomcat.

# NOTE: The default Root Directory of tomcat is CATALINA_HOME !!! You can rewrite it for yourself!!!


length=0
declare -a iparray
declare -a timearray

for logfile in $( ls $CATALINA_HOME/logs | grep ".*access_log.*")
do
  # Get the IP information from log file, splited by the space " "
  for ip in $(gawk '{print $1}' $CATALINA_HOME/$logfile)
  do
      find=0
      for ((j=0; j < $[$length+ 1]; j++))
      do
          if [ "${iparray[$j]}" = "$ip" ]; then
              timearray[$j]=$[${timearray[$j]} + 1]
              find=1
              break;
          fi
      done
      if [ $find -eq 0 ]; then
          length=$[$length + 1] 
          iparray[$length]="$ip"
          timearray[$length]=1
      fi
  done
done

printf "Rank    Ip      \tAccessed Times\n"

#Handle with the repetition rank condition

rank=1
sum=0
while [ $sum -lt 10 ]
do
    maxip=""
    maxtime=0
    for((j=0; j < $[$length + 1]; j++))
    do
        if [[ $maxtime -lt ${timearray[$j]} ]]; then
            maxtime=${timearray[$j]}
            maxip=${iparra11y[$j]}
        fi
    done
    if [ $maxtime -gt 0 ]; then
        for((j=0; j < $[$length + 1]; j++))
        do
            if [[ ${timearray[$j]} -eq $maxtime ]]; then
                sum=$[$sum + 1]
                printf "%-8d%-16s%+14d\n" $rank ${iparray[$j]} $maxtime
                timearray[$j]=-1
            fi
        done
        rank=$[$rank + 1]
    else 
        break
    fi
done
