# Hadoop集群搭建

## 完全分布式集群搭建
1. 创建目录
    ````
    sudo mkdir -p /export/servers
    sudo chmod 777 -R /export
    mkdir -p /export/data/hadoop
    ````
2. 下载安装包上传解压
    ````
    cd ~/src
    tar xvf ./hadoop-2.6.4.tar.gz -C /export/servers
    cd ~/apps
    ln -s /export/servers/hadoop-2.6.4/ ./hadoop
    ````
3. 修改配置
    - 修改vi hadoop-env.sh中的JAVA_HOME路径:export JAVA_HOME=/home/rocky/src/jdk
    - 分别修改core-site.xml hdfs-site.xml mapred-site.xml  yarn-site.xml  slaves 五个文件

    [core-site.xml] 
    
		<configuration>
                <property>
                        <name>fs.default.name</name>
                        <value>hdfs://node200:9000</value>
                </property>
                <property>
                        <name>hadoop.tmp.dir</name>
                        <value>/export/data/hadoop</value>
                </property>
        </configuration>
		
    [hdfs-site.xml]
    
		<configuration>
                <property>
                        <name>dfs.replication</name>
                        <value>3</value>
                </property>
                <property>
                        <name>dfs.name.dir</name>
                        <value>/export/data/hadoop/name</value>
                </property>
                <property>   
                        <name>dfs.data.dir</name>   
                        <value>/export/data/hadoop/data</value>   
                </property>   
                <property>
                        <name>dfs.namenode.secondary.http-address</name>
                        <value>node205:9001</value>
                </property>
                <property>    
                        <name>dfs.webhdfs.enabled</name>    
                        <value>true</value>    
                </property>    
                <property>    
                        <name>dfs.permissions</name>    
                        <value>false</value>    
                </property>    
        </configuration>
	
	[yarn-site.xml]

		<configuration>
                <property>
                        <name>yarn.resourcemanager.hostname</name>
                        <value>node200</value>
                </property>
                <!-- reducer获取数据的方式 -->
                <property>
                        <name>yarn.nodemanager.aux-services</name>
                        <value>mapreduce_shuffle</value>
                </property>
        </configuration>
		
	[mapred-site.xml]
	
		<configuration>
        <property>    
              <name>mapreduce.framework.name</name>    
              <value>yarn</value>    
        </property>  
		</configuration>
		
	[slaves]
	
		node201
        node202
        node203
        node204  
4. 分发jar包
     ````
     xsync /export/servers/hadoop-2.6.4/
     ````
5. 格式化namenode
    ````
    hadoop namenode -format
    ````
6. 启动验证
    ````
    start-all.sh
    - 外部UI验证--node200:50070
    -   [rocky@node200 ~]$ xcall jps
        -------- localhost -------
        [jps]
        13248 NameNode
        13508 ResourceManager
        13770 Jps
        ---------- node201 ---------
        [jps]
        11985 Jps
        11734 DataNode
        11838 NodeManager
        ---------- node202 ---------
        [jps]
        11802 NodeManager
        11949 Jps
        11695 DataNode
        ---------- node203 ---------
        [jps]
        11799 NodeManager
        11946 Jps
        11695 DataNode
        ---------- node204 ---------
        [jps]
        11956 Jps
        11703 DataNode
        11807 NodeManager
        ---------- node205 ---------
        [jps]
        11505 SecondaryNameNode
    11571 Jps
    ````