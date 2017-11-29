package com.rocky.mr.wordcountcombiner;

import org.apache.hadoop.hdfs.qjournal.protocol.QJournalProtocolProtos;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/29/17
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
    Text text = new Text();
    IntWritable count = new IntWritable();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {

        String[] words = value.toString().split(" ");

        for (String word : words)
        {
            text.set(word);
            count.set(1);
            context.write(text, count);
        }
    }
}
