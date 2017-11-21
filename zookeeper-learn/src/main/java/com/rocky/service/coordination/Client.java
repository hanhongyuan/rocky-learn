package com.rocky.service.coordination;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/21
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 * Description:服务器上下线动态感知客户端
 */
public class Client
{
    private static final String ZK_CLUSTER_INFO = "node110:2181,node111:2181,node112:2181";
    private static final int TIME_OUT = 2 * 1000;
    private static final String GROUP_NOED = "/servers";

    ZooKeeper zkClient = null;
    private volatile List<String> serverList = null;

    /**
     * 获取与zk的连接并注册监听
     * @throws IOException
     */
    private void getConnect() throws IOException
    {
        zkClient = new ZooKeeper(ZK_CLUSTER_INFO, TIME_OUT, new Watcher()
        {
            public void process(WatchedEvent watchedEvent)
            {
                try
                {
                    //当服务器有宕机时，zk会删除对应的服务器注册信息，clint因为已经注册了监听，此方法会被回调然后重新获取服务器列表
                    getServersList();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取zk上servers目录下的服务器列表
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void getServersList() throws KeeperException, InterruptedException
    {
        List<String> children = zkClient.getChildren(GROUP_NOED, true);
        List<String> servers = new ArrayList<String>();
        for (String child : children)
        {
            byte[] data = zkClient.getData(GROUP_NOED + "/" + child, false, null);
            servers.add(new String(data));
        }
        serverList = servers;

        System.out.println(servers);
        System.out.println("Client.getServersList" + "===by rocky's log");


    }
    public static void main(String [] args) throws Exception
    {
        Client client = new Client();
        //获取zk连接并监听
        client.getConnect();
        //获取servers目录下的服务器信息
        client.getServersList();
        //请求服务器
        client.doBusiness();
    }

    /**
     * 模拟业务逻辑
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
