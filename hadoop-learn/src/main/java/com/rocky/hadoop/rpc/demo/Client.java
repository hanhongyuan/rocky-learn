package com.rocky.hadoop.rpc.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/27
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Client
{
    public static void main(String [] args) throws IOException
    {
        ClientServiceProtocol proxy = RPC.getProxy(ClientServiceProtocol.class, 1L, new InetSocketAddress("localhost", 8888), new Configuration());
        String res = proxy.service("client requst...");
        System.out.println(res);

    }
}
