
ip=`nslookup l-test.dev.cn1 | grep Address | tail -n 1 | awk '{print $2}'`

set timeout 20

set local_file dir1
set remote_path /tmp
set remote_user root
set passwd linux2

spawn scp $local_file $remote_user@$ip:$remote_path

expect{
    "*assword:"{
        set timeout 1000
        send "$passwd\r"
        exp_continue
    }
    "*es/no"{
        send "yes\r"
        exp_continue
    }
}

