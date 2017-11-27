package com.rocky.hadoop.rpc.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/27
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class PublishServiceUtil
{
    public static void main(String [] args) throws IOException
    {
        RPC.Builder builder = new RPC.Builder(new Configuration());
        builder.setBindAddress("localhost")
                .setPort(8888)
                .setProtocol(ClientServiceProtocol.class)
                .setInstance(new MyService());

        RPC.Server server = builder.build();
        server.start();
    }
}
