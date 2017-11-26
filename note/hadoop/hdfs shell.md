# HDFS简介
　HDFS是分布式文件系统，用于分布式存储海量数据。

**个人总结**
```
    Hadoop集群安装成功后可以通过start-dfs.sh单独启动hdfs集群，启动成功后可以通过jps查看namenode和datanode对应节点的进程，同时也要通往web浏览器访问namenode_ip:50070来查看整个hdfs集群状态。
    
    HDFS是一个分布式文件系统，是对文件系统的一个抽象和统一管理，但是其文件存储最终还是会存储到分布式系统中集群的集群的硬盘上。但是通过HDFS管理存储可以提高数据的可靠性。
    
    首先对于存储在HDFS上的文件，会根据文件大小进行切块。默认切块大小为128M。即：小于128M的文件默认为一块，根据副本数将此文件分别存储在不同的datanode节点上，实际存储位置为Hadoop配置的数据目录里。
    对于大于128M的文件首先文件会按照128M切块，每块根据副本数存储在相同数量的机器节点上。假设一个200M的文件会被且成两块，按照默认副本数为3的情况，则该文件在整个集群中对应了六个文件块。

```

# HDFS Shell 命令
1. 查看hdfs目录或文件
```
[rocky@node200 ~]$ hadoop fs -lsr /
```
2. 查看hdfs文件系统内文件内容
```
[rocky@node200 ~]$ hadoop fs -cat /test/test.txt
hello hdfs
```
3. 上传文件到hdfs
```
[rocky@node200 ~]$ hadoop fs -put ./test.txt /test
```
4. 从hdfs文件系统下载文件到本地
```
[rocky@node200 ~]$ hadoop fs -get /test/test.txt ./
```
5. 在hdfs文件系统创建目录
```
[rocky@node200 ~]$ hadoop fs -mkdir /test
```
6. 追加内容到hdfs文件系统的某一文件内
```
[rocky@node200 ~]$ hadoop fs -appendToFile append.txt /test/test.txt
```
7. 修改hdfs文件系统下的文件或者文件夹的属主
```
[rocky@node200 ~]$ hadoop fs -chown rocky:rocky /test/test.txt
```
8. 修改hdfs文件系统下的文件或者文件夹的权限
```
[rocky@node200 ~]$ hadoop fs -chmod 777 /test/test.txt
```
9. 查看hdfs文件系统下的磁盘空间使用情况
```
[rocky@node200 ~]$ hadoop fs -df -h /
```
10. 查看hdfs文件系统下的指定目录下文件或文件夹占用空间情况
```
[rocky@node200 ~]$ hadoop fs -du -s -h hdfs://node200:9000/* 
```
11. 修改hdfs文件系统中文件副本数
```
[rocky@node200 ~]$ hadoop fs -setrep 4 /test/test.txt
Replication 4 set: /test/test.txt
如果设置的副本数大于datanode节点数，实际副本数等于datanode节点数的副本数。不会冗余存储。
```

