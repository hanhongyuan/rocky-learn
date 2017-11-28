package com.rocky.mr.flowsum;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/28
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 * Description:流量对象bean  要实现序列化
 */
public class FlowBean implements Writable
{
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    public FlowBean()
    {
    }

    public FlowBean(long upFlow, long downFlow)
    {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    @Override
    public String toString()
    {
        return "FlowBean{" +
                "upFlow=" + upFlow +
                ", downFlow=" + downFlow +
                ", sumFlow=" + sumFlow +
                '}';
    }

    public long getSumFlow()
    {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow)
    {
        this.sumFlow = sumFlow;
    }


    public long getUpFlow()
    {
        return upFlow;
    }

    public void setUpFlow(long upFlow)
    {
        this.upFlow = upFlow;
    }

    public long getDownFlow()
    {
        return downFlow;
    }

    public void setDownFlow(long downFlow)
    {
        this.downFlow = downFlow;
    }

    public void write(DataOutput out) throws IOException
    {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    public void readFields(DataInput in) throws IOException
    {
        this.upFlow = in.readLong();
        this.downFlow = in.readLong();
        this.sumFlow = in.readLong();
    }
}
