package com.rocky.mr.wordcount;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/28
 * Time: 12:50
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>
{
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
    {
        int count = 0;
        for (IntWritable value : values)
        {
            count += value.get();
        }
        context.write(key, new IntWritable(count));
    }
}
