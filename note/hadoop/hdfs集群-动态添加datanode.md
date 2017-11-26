1. 配置系统环境
    ````
        主机名配置
        ssh免密配置
        环境变量等
    ````
2. 修改namenode节点slaves文件
     ````
        将新增节点添加到slaves
     ````
3. 在namenode节点上,将hadoop-xxx复制到新节点上,并在新节点上删除data和logs目录中的文件
     ````
        $ rm -rf $HADOOP_HOME/logs/*
        $ rm -rf $HADOOP_HOME/data/*
     ````
4. 启动新datanode的datanode进程
     ````
        hadoop-daemon.sh start datanode
     ````        
5. 启动新datanode的datanode进程
     ````
        hadoop-daemon.sh start datanode
     ````  
6.  在namenode查看当前集群情况,确认信节点已经正常加入
    - 命令方式
     ````
        $ hdfs dfsadmin -report
     ````  
    - 以web方式
        ````
           node200:50070
        ````  
7. 在namenoe上设置 hdfs 的负载均衡
     ````
        $HADOOP_HOME/bin/start-balancer.sh -t 10%
        -t参数后面跟的是HDFS达到平衡状态的磁盘使用率偏差值。如果机器与机器之间磁盘使用率偏差小于10%，那么我们就认为HDFS集群已经达到了平衡状态。
     ````                  
8. 启动新节点的nodemanager进程
     ````
        yarn-daemon.sh start nodemanager
     ````                   