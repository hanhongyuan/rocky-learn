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
 * Description:
 */
public class ClientOps
{
      FileSystem fs = null;
   @Before
   public void init() throws IOException
   {
      Configuration conf = new Configuration();
      fs = FileSystem.get(conf);
   }

   @Test
   public void testUpload() throws IOException
   {
      fs.copyFromLocalFile(new Path("test_data/words.log"), new Path("/test/"));
      fs.close();
   }
}
