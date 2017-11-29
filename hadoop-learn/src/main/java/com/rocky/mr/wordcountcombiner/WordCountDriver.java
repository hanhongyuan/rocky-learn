package com.rocky.mr.wordcountcombiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/29/17
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class WordCountDriver
{
    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException
    {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://node200:9000");//set distribute filesystem
        conf.set("mapreduce.framework.name", "yarn");//set resource framework
        conf.set("yarn.resourcemanager.hostname", "node200");//set RM hostname
        conf.set("yarn.nodemanager.aux-services", "mapreduce_shuffle");

        Job job = Job.getInstance(conf);

        job.setJarByClass(WordCountDriver.class);
        //set input data and out data
        FileInputFormat.setInputPaths(job,new Path("hdfs://node200:9000/wordcount/input"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://node200:9000/wordcount/output"));

        //set mapper and reduce class
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //set mapper out
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //set reducer out
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setCombinerClass(WordCountCombiner.class);
        boolean res = job.waitForCompletion(true);
        System.exit(res ? 0 : 1);

    }
}
