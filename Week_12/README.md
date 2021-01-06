# Redis主从配置

### 拉取redis镜像
`docker pull redis`

### 启动容器
```
docker run --name redis-6379 -p 6379:6379 -d redis 
docker run --name redis-6380 -p 6380:6379 -d redis 
docker run --name redis-6381 -p 6381:6379 -d redis
```

### 查看容器ip
`docker inspect xxx`

redis-6379 172.17.0.2:6379 

redis-6380 172.17.0.3:6379

redis-6381 172.17.0.4:6379

### 执行SLAVEOF
`SLAVEOF 172.17.0.2 6379`


#### 配置 Sentinel 哨兵

#### sentinel配置文件如下：
```
port 16381
daemonize no
pidfile /var/run/redis-sentinel.pid
logfile ""
dir /tmp
sentinel monitor mymaster 192.168.101.104 6381 2
sentinel down-after-milliseconds mymaster 10000
sentinel failover-timeout mymaster 30000
sentinel parallel-syncs mymaster 1
```

#### 命令运行记录如下：
```
docker cp /etc/sentinel.conf redis1:/etc/sentinel.conf

42:X 06 Jan 2021 14:28:20.938 # +monitor master mymaster 10.0.0.3 6381 quorum 2
42:X 06 Jan 2021 14:28:20.941 * +slave slave 172.17.0.1:6379 172.17.0.1 6379 @ mymaster 10.0.0.3 6381
42:X 06 Jan 2021 14:28:30.951 # +sdown slave 172.17.0.1:6379 172.17.0.1 6379 @ mymaster 10.0.0.3 6381
```
