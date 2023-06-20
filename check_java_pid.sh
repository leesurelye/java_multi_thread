java_count=`ps -C java --no-header | wc -l`
if [ $java_count -eq 0 ];then
    java /Users/leesure/Documents/aloudata/leetcode_java/src/main/java/org/exception/Keepalived
    sleep 1

    if [ `ps -C java --no-header | wc -l` -eq 0 ];then
        /usr/local/src/keepalived/etc/rc.d/init.d/keepalived stop
    fi
fi