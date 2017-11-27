package com.rocky.hadoop.rpc.login;

import com.rocky.hadoop.rpc.demo.ClientServiceProtocol;
import com.rocky.hadoop.rpc.demo.MyService;
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
                .setProtocol(IUserLoginService.class)
                .setInstance(new UserLoginServiceImpl());

        RPC.Server server = builder.build();
        server.start();
    }
}
