#########################################################################
# File Name: storm.sh
# Author: rocky
# Mail: wanyouxian@gmail.com
# Created Time: Tue 31 Oct 2017 10:56:36 PM CST
#########################################################################
#!/bin/bash
#
#准备工作：
#机器配置,ip-机器名 在hosts中配置好
#配置各节点storm 的STORM_HOME和ZOOKEEPER_HOME
#SSH免密码访问
#
#脚本在主节点下运行
#

usage="Usage: $0 (start|stop|status)"

if [ $# -lt 1 ]; then
echo $usage
exit 1
fi

behave=$1
#zk的节点，3台，不在主节点启动
zookeeperServers='node110 node111 node112'


#启动各个zookeeper server
for i in $zookeeperServers;
do
echo "=============$i $behave=================="
        ssh -T $i << EOF
        source /etc/profile
        cd \$ZOOKEEPER_HOME
        ./bin/zkServer.sh $behave
EOF
        echo  $behave zookeeper$i...[ done ]
        sleep 1
done

#检查其余各个 QuorumPeerMain 是否启动
#如果刚才没有正常启动，则启动
#for k in $zookeeperServers;
#do
#       ssh -T $k << EOF
#       source /etc/profile
#       count=`jps |grep QuorumPeerMain |wc -l`
#       if [ \$count = 0 ]
#       then
#               cd \$ZOOKEEPER_HOME
#               ./bin/zkServer.sh $behave
#       fi
#EOF
#done
#检查各个节点 QuorumPeerMain 状态
#       ssh rocky@$j "source /etc/profilecd;  cd $ZOOKEEPER_HOME; ./bin/zkServer.sh status"
#for j in $zookeeperServers;
#do
#echo "=============$j Status=================="
#       ssh -T $j << EOF
#       source /etc/profile
#       cd \$ZOOKEEPER_HOME
#       ./bin/zkServer.sh $behave
#EOF
#done