package com.rocky.mr.flowsumsortedbysumflow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/29/17
 * Time: 9:02 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class FlowSumSortedReducer extends Reducer<FlowBean, Text, Text, FlowBean>
{

    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException
    {

        context.write(values.iterator().next(), key);
    }
}
