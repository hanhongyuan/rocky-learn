package com.rocky.hdfs.basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/22
 * Time: 21:33
 * To change this template use File | Settings | File Templates.
 * Description:该类中通过Hadoop命令的Java接口进行文件上传和下载
 */
public class ClientCMDOps
{
      FileSystem fs = null;
   @Before
   public void init() throws IOException
   {
      Configuration conf = new Configuration();
      conf.set("fs.defaultFS", "hdfs://node200:9000");
      fs = FileSystem.get(conf);
   }

   @Test
   public void testUpload() throws IOException
   {
      fs.copyFromLocalFile(new Path(""), new Path("/test/"));
      fs.close();
   }
   @Test
   public void testDownload() throws IOException {
      fs.copyToLocalFile(new Path("/test/test.txt"), new Path("/home/rocky/myself/rocky-learn/test_data/"));
      fs.close();
   }
}
