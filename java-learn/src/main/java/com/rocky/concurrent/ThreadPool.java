package com.rocky.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/23
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class ThreadPool
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();//Executors.newFixedThreadPool(5);

     //   Executors.newCachedThreadPool()
//Executors.newScheduledThreadPool()
        for (int i = 0; i < 10; i ++)
        {
            System.out.println("Thread " + i + "已经提交");
            executorService.execute(new MyRunnalble());
        }
    }


}
class  MyRunnalble implements Runnable
{
    public void run()
    {
        try
        {
            Thread.sleep(10 * 1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}