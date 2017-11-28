package com.rocky.mr.flowsum;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/28
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean>
{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {

        String line = value.toString();
        String[] fields = line.split("\t");
        //取出手机号码
        String phoneNum = fields[1];
        //取出上下行流量
        long upFlow = Long.parseLong(fields[fields.length - 3]);
        long downFlow = Long.parseLong(fields[fields.length - 2]);

        context.write(new Text(phoneNum), new FlowBean(upFlow, downFlow));
    }
}
