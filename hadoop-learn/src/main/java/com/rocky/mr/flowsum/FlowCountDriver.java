package com.rocky.mr.flowsum;

import com.rocky.mr.wordcount.WordCountMapper;
import com.rocky.mr.wordcount.WordCountReducer;
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
 * User: Rocky
 * Date: 2017/11/28
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 * Description:相当于yarn集群的客户端
 */
public class FlowCountDriver
{
    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException
    {
        Configuration conf = new Configuration();
        conf.addResource(new Path("hadoop-cluster.xml"));
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        Job job = Job.getInstance(conf);

        job.setJarByClass(FlowCountDriver.class);
        //指定map和reduce的类
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);
        //指定map输出kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        //指定最终输出kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);


        FileInputFormat.setInputPaths(job, new Path("hdfs://node200:9000/flowcount/input"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://node200:9000/flowcount/output"));

        boolean res = job.waitForCompletion(true);

        System.exit(res ? 0 : 1);
    }
}
