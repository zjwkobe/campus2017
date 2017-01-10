awk '{print $1}' *access_log*.txt | sort | uniq -c | sort -rn | head -n 10
