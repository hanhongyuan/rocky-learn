package com.rocky.hadoop.rpc.demo;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/27
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class MyService implements ClientServiceProtocol
{

    public String service(String msg)
    {
        System.out.println("receive msg : " + msg);
        return handlerMsg(msg);

    }

    private String handlerMsg(String msg)
    {
        return "after handler : " + msg;
    }
}
