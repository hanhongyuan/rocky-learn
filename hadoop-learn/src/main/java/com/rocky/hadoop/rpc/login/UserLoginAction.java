package com.rocky.hadoop.rpc.login;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/27
 * Time: 15:06
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class UserLoginAction
{
    public static void main(String [] args) throws IOException
    {
        IUserLoginService proxy = RPC.getProxy(IUserLoginService.class, 100L, new InetSocketAddress("localhost", 8888), new Configuration());
        String status = proxy.login("rocky", "rockypasswd");
        System.out.println(status);

    }
}
