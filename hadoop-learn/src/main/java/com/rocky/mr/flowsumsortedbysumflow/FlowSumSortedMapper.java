package com.rocky.mr.flowsumsortedbysumflow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/29/17
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class FlowSumSortedMapper extends Mapper<LongWritable, Text, FlowBean, Text>
{
    FlowBean bean = new FlowBean();
    Text text = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
        String[] fields = value.toString().split("\t");
        String phoneNum = fields[0];
        long sumUpFlow = Long.parseLong(fields[1]);
        long sumDownFlow = Long.parseLong(fields[2]);

        bean.setUpFlow(sumUpFlow);
        bean.setDownFlow(sumDownFlow);
        bean.setSumFlow(sumUpFlow + sumDownFlow);
        text.set(phoneNum);
        context.write(bean, text);
    }
}
