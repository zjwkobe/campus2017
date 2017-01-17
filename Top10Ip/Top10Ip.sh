#! /bin/bash
log_path='/etc/tomcat/logs/'
if [ -s $log_path ]
		then
				cd $log_path
				find . -name '*.log' | xargs cat | cut -d ' ' -f1 | sort | uniq -c | sort -k1,1nr | head -10
else 
		echo -e 'e[0;31m' "No log records" '\e[0;0m'
fi
