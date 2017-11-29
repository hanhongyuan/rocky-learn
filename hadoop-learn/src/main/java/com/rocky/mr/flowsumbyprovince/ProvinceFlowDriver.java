package com.rocky.mr.flowsumbyprovince;

import com.rocky.mr.flowsum.FlowBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/28/17
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */

public class ProvinceFlowDriver
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
    {

        Configuration conf = new Configuration();
      //  conf.addResource(new Path("hadoop-cluster.xml"));
        conf.set("fs.defaultFS", "hdfs://node200:9000");
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.resourcemanager.hostname", "node200");
        conf.set("yarn.nodemanager.aux-services", "mapreduce_shuffle");
       // conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        Job job = Job.getInstance(conf);

        job.setJarByClass(ProvinceFlowDriver.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        org.apache.hadoop.mapreduce.lib.input.FileInputFormat.setInputPaths(job, new Path("hdfs://node200:9000/flowcount/input"));
        org.apache.hadoop.mapreduce.lib.output.FileOutputFormat.setOutputPath(job, new Path("hdfs://node200:9000/flowcount/output"));
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        job.setMapperClass(ProvinceFlowMapper.class);
        job.setReducerClass(ProvinceFlowReducer.class);
        job.setPartitionerClass(ProvincePartitioner.class);
        job.setNumReduceTasks(5);
        boolean res = job.waitForCompletion(true);

        System.exit(res ? 0 : 1);
    }
}
