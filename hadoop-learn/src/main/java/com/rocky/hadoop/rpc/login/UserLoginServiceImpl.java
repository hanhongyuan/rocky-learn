package com.rocky.hadoop.rpc.login;


/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/27
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class UserLoginServiceImpl implements IUserLoginService
{
    public String login(String username, String passwd)
    {

        return username + " logged in successfully...";
    }
}
