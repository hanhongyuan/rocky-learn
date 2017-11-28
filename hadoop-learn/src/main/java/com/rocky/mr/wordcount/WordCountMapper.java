package com.rocky.mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/28
 * Time: 12:41
 * To change this template use File | Settings | File Templates.
 * Description: WordCount的mapper
 */

/**
 *
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
    Text text = new Text();
    IntWritable value = new IntWritable();

    //maptask会对每一行的输入数据调用一次map方法
    @Override
    protected void map(LongWritable key, Text line, Context context) throws IOException, InterruptedException
    {
        String[] words = line.toString().split(" ");
        for (String word : words)
        {
            text.set(word);
            value.set(1);
            context.write(text, value);
        }

    }
}
