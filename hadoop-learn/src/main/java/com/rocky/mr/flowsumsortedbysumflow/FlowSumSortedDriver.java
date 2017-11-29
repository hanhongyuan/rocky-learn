package com.rocky.mr.flowsumsortedbysumflow;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/29/17
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class FlowSumSortedDriver
{
    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException
    {
        Configuration conf = new Configuration();
        //  conf.addResource(new Path("hadoop-cluster.xml"));
        conf.set("fs.defaultFS", "hdfs://node200:9000");
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.resourcemanager.hostname", "node200");
        conf.set("yarn.nodemanager.aux-services", "mapreduce_shuffle");
        // conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        Job job = Job.getInstance(conf);

        job.setJarByClass(FlowSumSortedDriver.class);

        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        org.apache.hadoop.mapreduce.lib.input.FileInputFormat.setInputPaths(job, new Path("hdfs://node200:9000/flowcount/output"));
        org.apache.hadoop.mapreduce.lib.output.FileOutputFormat.setOutputPath(job, new Path("hdfs://node200:9000/flowcount/sortoutput"));
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        job.setMapperClass(FlowSumSortedMapper.class);
        job.setReducerClass(FlowSumSortedReducer.class);
        boolean res = job.waitForCompletion(true);

        System.exit(res ? 0 : 1);
    }
}
