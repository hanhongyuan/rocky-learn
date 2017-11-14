package com.rocky.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/14
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 * Description:测试Java NewCachedThreadPool Demo
 */
public class NewCachedThreadPoolDemo
{
    public static void main(String [] args) throws InterruptedException
    {
        //创建一个数量可变的线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i ++)
        {
            pool.execute(new Runnable()
            {
                public void run()
                {
                    System.out.println("thread name: " + Thread.currentThread().getName());
                    try
                    {
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }

        Thread.sleep(10 * 1000);
        System.out.println("====================================");

        for (int i = 0; i < 10; i ++)
        {
            pool.execute(new Runnable()
            {
                public void run()
                {
                    System.out.println("thread name: " + Thread.currentThread().getName());
                    try
                    {
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        pool.shutdown();

    }
}
