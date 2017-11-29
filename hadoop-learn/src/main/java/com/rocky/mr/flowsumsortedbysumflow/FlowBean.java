package com.rocky.mr.flowsumsortedbysumflow;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/29/17
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class FlowBean implements WritableComparable<FlowBean>
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


    public int compareTo(FlowBean flowBean)
    {
        return this.sumFlow>flowBean.getSumFlow()?-1:1;	//从大到小, 当前对象和要比较的对象比, 如果当前对象大, 返回-1, 交换他们的位置(自己的理解)
    }
}