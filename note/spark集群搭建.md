# SPARK

## Spark 相关概念
1. DAG--有向无环图
2. 
3. 
## Spark 集群搭建
- 下载spark安装包
- 创建目录并解压
````
sudo mkdir -p /export/servers
sudo chmod 777 -R /export
tar xvf ./apache-storm-0.9.5.tar.gz -C /export/servers
````
- 修改配置文件
- 分发安装包
````
cd /export/servers/
xsync ./apache-storm-0.9.5
````
- 创建软连接
````
cd ~/apps/
ln -s /export/servers/apache-storm-0.9.5 ./storm
````
- 编写启动脚本

