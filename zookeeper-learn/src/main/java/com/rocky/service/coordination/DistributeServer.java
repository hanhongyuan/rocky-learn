package com.rocky.service.coordination;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/21
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 * Description:服务器上下线动态感知服务端
 */
public class DistributeServer
{
    private static final String ZK_CLUSTER_INFO = "node110:2181,node111:2181,node112:2181";
    private static final int TIME_OUT = 2 * 1000;
    private static final String GROUP_NODE = "/servers";
    private ZooKeeper zkClient = null;

    /**
     * 服务器获取与zk的连接
     * @throws IOException
     */
    public void getConnect() throws IOException
    {
        zkClient = new ZooKeeper(ZK_CLUSTER_INFO, TIME_OUT, new Watcher()
        {
            public void process(WatchedEvent watchedEvent)
            {

            }
        });
    }

    /**
     * 获取服务器本机信息，用于注册zk时使用
     * @return
     * @throws UnknownHostException
     */
    public String getServerInfo() throws UnknownHostException
    {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String hostName = inetAddress.getHostName();
        String hostAddress = inetAddress.getHostAddress();
        System.out.println(hostAddress + hostName);
        System.out.println("DistributeServer.getServerInfo" + "===by rocky's log");
        
        return hostName + "-" + hostAddress;
    }

    /**
     * 服务器到zk上注册信息
     * @throws UnknownHostException
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void registerServer() throws UnknownHostException, KeeperException, InterruptedException
    {
        String serverInfo = getServerInfo();
        Stat stat = zkClient.exists(GROUP_NODE, false);
        if (stat == null){
            zkClient.create(GROUP_NODE, GROUP_NODE.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        int i = new Random().nextInt(32);
        String registerPath = zkClient.create(GROUP_NODE + "/server-" + serverInfo + String.valueOf(i), serverInfo.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(registerPath);

        System.out.println("DistributeServer.registerServer" + "===by rocky's log");
        
    }
    public static void main(String [] args) throws Exception
    {
        DistributeServer server = new DistributeServer();
        //1.获取zk连接
        server.getConnect();
        //2.注册信息
        server.registerServer();
        //启动业务逻辑
        server.doBusiness();
    }

    /**
     * 模拟业务逻辑方法
     * @throws InterruptedException
     */
    private void doBusiness() throws InterruptedException
    {
        while (true)
        {
            System.out.println("DistributeServer.doBusiness" + "===by rocky's log");
            
            Thread.sleep(Long.MAX_VALUE);
        }
    }
}
