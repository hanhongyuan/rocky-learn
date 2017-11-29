package com.rocky.mr.flowsumbyprovince;

import com.rocky.mr.flowsum.FlowBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/28/17
 * Time: 10:57 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class ProvinceFlowMapper extends Mapper<LongWritable, Text, Text, FlowBean>
{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
        String[] fields = value.toString().split("\t");
        String phoneNum = fields[1];
        long upFlow = Long.parseLong(fields[fields.length - 3]);
        long downFlow = Long.parseLong(fields[fields.length - 2]);

        context.write(new Text(phoneNum), new FlowBean(upFlow, downFlow));
    }
}
