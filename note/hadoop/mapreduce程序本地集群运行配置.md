# 本地运行模式配置
````
conf.set("fs.defaultFS", "file:///");
conf.set("mapreduce.framework.name", "local");
````

# 集群运行模式配置
````
conf.set("fs.defaultFS", "hdfs://node200:9000");//set distribute filesystem
conf.set("mapreduce.framework.name", "yarn");//set resource framework
conf.set("yarn.resourcemanager.hostname", "node200");//set RM hostname
conf.set("yarn.nodemanager.aux-services", "mapreduce_shuffle");
````
