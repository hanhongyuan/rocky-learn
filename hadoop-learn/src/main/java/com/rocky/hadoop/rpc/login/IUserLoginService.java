package com.rocky.hadoop.rpc.login;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/27
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public interface IUserLoginService
{
    final long versionID = 100L;
    String login(String username, String passwd);
}
