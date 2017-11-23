package com.rocky.basic;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/21
 * Time: 20:05
 * To change this template use File | Settings | File Templates.
 * Description:对zk数据的基本操作增删改查
 */
public class ZookeeperClient
{

    private static final String connectString = "node110:2181,node111:2181,node112:2181";
    private static final int sessionTimeOut = 2 * 1000;

    ZooKeeper zkClient = null;

    @Before
    public void init() throws IOException
    {
        zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher()
        {
            public void process(WatchedEvent watchedEvent)
            {
                //收到事件后的回调函数--此处主要进行事件监听处理逻辑代码
                System.out.println("EventType: " + watchedEvent.getType() + " ======== EventPath: " + watchedEvent.getPath());
                switch (watchedEvent.getType())
                {
                    case NodeCreated:
                        System.out.println(watchedEvent.getPath() + " 路径下发生了NodeCreated");
                        break;
                    case NodeDeleted:
                        System.out.println(watchedEvent.getPath() + " 路径下发生了NodeDeleted");
                        break;

                    case NodeChildrenChanged:
                        System.out.println(watchedEvent.getPath() + " 路径下发生了NodeChildrenChanged");
                        try
                        {
                            zkClient.getChildren("/", true);
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    case NodeDataChanged:
                        System.out.println(watchedEvent.getPath() + " 路径下发生了NodeDataChanged");
                        try
                        {
                            zkClient.getData("/idea", true, null);
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        break;


                }

            }
        });
    }

    /**
     * 创建znode节点
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void testCreate() throws KeeperException, InterruptedException
    {
        /**
         * 参数一：创建znode路径
         * 参数二：创建节点附加的数据--该数据可以是任何类型，但是必须是byte类型
         * 参数三：创建节点的权限
         * 参数四：创建节点的类型
         * 返回值：创建成功后的节点路径
         */
        String nodePath = zkClient.create("/idea", "hello zk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("node : " + nodePath);
    }

    @Test
    public void testExist() throws KeeperException, InterruptedException
    {
        /**
         * 返回值stat--如果不存在则stat为null
         */
        testCreate();
        Stat stat = zkClient.exists("/idea", false);
        System.out.println(stat == null ? "节点不存在" : "节点存在");

        
    }
    @Test
    public void testGetChildren() throws KeeperException, InterruptedException
    {
        /**
         * 参数一：获取znode节点的路径
         * 参数二：是否监听该znode
         */
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children)
        {
            System.out.println(child);

        }
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void testGetData() throws KeeperException, InterruptedException, UnsupportedEncodingException
    {
        byte[] data = zkClient.getData("/idea", true, null);
        System.out.println(new String(data, "UTF-8"));
       // Thread.sleep(Long.MAX_VALUE);

    }

    /**
     * 删除znode
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void testDeleteZnode() throws KeeperException, InterruptedException
    {
        /**
         * 参数二：指定要删除的版本， -1表示删除所有版本
         */
        zkClient.delete("/testDelete", -1);
    }

    @Test
    public void testSetData() throws KeeperException, InterruptedException, UnsupportedEncodingException
    {
        testCreate();
        testGetData();
       zkClient.setData("/idea", "i love you zk".getBytes(), -1);
        testGetData();

    }

}
