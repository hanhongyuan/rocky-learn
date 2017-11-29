package com.rocky.mr.join;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rocky
 * Date: 11/29/17
 * Time: 7:24 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class JoinMapper extends Mapper<LongWritable, Text, Text, InfoBean>
{
    InfoBean bean = new InfoBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
        String[] fields = value.toString().split("\t");
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        String name = inputSplit.getPath().getName();
        String pid = "";
        if (name.startsWith("product"))
        {
            pid = fields[0];
            bean.set(fields[0], fields[1], fields[2], fields[3], "", "", "", "", "1");
        }
        else {
            pid = fields[2];
            bean.set("", "", "", "", fields[0], fields[1], fields[2], fields[3],  "0");
        }
        context.write(new Text(pid), bean);

    }
}
