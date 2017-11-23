package com.rocky.concurrent;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/14
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class CallAbleDemo
{
    public static void main(String [] args) throws InterruptedException, ExecutionException
    {

        //创建一个固定大小的线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i ++)
        {
            pool.execute(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        System.out.println("thread name: " + Thread.currentThread().getName());

                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }


        Future<Integer> future = pool.submit(new Callable<Integer>()
        {
            public Integer call() throws Exception
            {
                Thread.sleep(10 * 1000);
                return 100;
            }
        });

        boolean status = future.isDone();
        System.out.println("status" + status);

        while (!future.isDone())
        {
            Thread.sleep(1 * 1000);
        }
        Integer integer = future.get();
        System.out.println("res: " + integer);

        pool.shutdown();
    }
}
