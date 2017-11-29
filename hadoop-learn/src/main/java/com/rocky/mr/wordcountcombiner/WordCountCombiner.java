package com.rocky.mr.wordcountcombiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/29/17
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable>
{
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
    {

        int sum = 0;
        for (IntWritable count : values)
        {
            sum += count.get();
        }
        context.write(key, new IntWritable(sum));
    }
}
