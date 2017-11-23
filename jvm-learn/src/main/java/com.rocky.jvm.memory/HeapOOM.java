package com.rocky.jvm.memory;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/23
 * Time: 10:21
 * To change this template use File | Settings | File Templates.
 * Description:Java 堆内存溢出异常测试
 */
public class HeapOOM
{
    static class OOMObject
    {
    }

    public static void main(String[] args)
    {
        ArrayList<OOMObject> list = new ArrayList<OOMObject>();
        while (true)
            list.add(new OOMObject());
    }
}
