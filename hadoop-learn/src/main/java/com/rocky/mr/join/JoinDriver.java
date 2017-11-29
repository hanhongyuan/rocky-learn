package com.rocky.mr.join;

import com.rocky.mr.wordcount.WordCountMapper;
import com.rocky.mr.wordcount.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/28
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 * Description:相当于yarn集群的客户端
 */
public class JoinDriver
{
    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException
    {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJar("/home/rocky/myself/rocky-learn/hadoop-learn/target/hadoop-learn-1.0-SNAPSHOT.jar");

        job.setJarByClass(JoinDriver.class);
        //指定map和reduce的类
        job.setMapperClass(JoinMapper.class);
        job.setReducerClass(JoinReducer.class);
        //指定map输出kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(InfoBean.class);

        //指定最终输出kv类型
        job.setOutputKeyClass(InfoBean.class);
        job.setOutputValueClass(NullWritable.class);


        FileInputFormat.setInputPaths(job, new Path("hdfs://node200:9000/join/input"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://node200:9000/join/output"));

        boolean res = job.waitForCompletion(true);

        System.exit(res ? 0 : 1);
    }
}
