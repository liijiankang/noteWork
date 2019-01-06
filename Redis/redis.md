#### redis
##### 安装
    1.	解压压缩包
    tar -zxvf redis-3.2.0.tar.gz
    2.	安装gcc
    yum  install  gcc
    检查gcc安装是否成功
    rpm -qa |grep gcc
    3.	cd进入redis目录下的：deps目录执行：
    make hiredis lua linenoise jemalloc
    4.	进入文件夹make安装
    cd redis-3.2.0
    
    make MALLOC=libc
    
    5.	后台运行redis
    src/redis-server redis.conf&
    检查redis服务启动情况
    ps -ef |grep redis
    
    6.	客户端连接redis
    redis-cli -p 6379
    
    7.	设置密码
    查看：
    config get requirepass
    设置
    config set requirepass 123456
    quit
    
    redis-cli
    ping
    auth 123456
    
    
    远程访问:
    编辑配置文件：redis.conf
    1. 找到并注释：bind 127.0.0.1
    # bind 127.0.0.1
    2. 把参数protected-mode设置成no
    protected-mode no

