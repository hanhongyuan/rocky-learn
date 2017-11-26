package com.rocky.hdfs.basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky
 * Date: 2017/11/24
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class ClientJavaOps
{
        FileSystem fs = null;
    @Before
    public void init() throws IOException
    {
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("fs.defaultFS", "hdfs://node200:9000");
        fs = FileSystem.get(conf);
    }

    @Test
    public void readHDFSFile() throws IOException
    {
        Path path = new Path("/test/test.txt");
        FSDataInputStream fsIn = fs.open(path);
        IOUtils.copyBytes(fsIn, System.out, 4096, false);
        fsIn.close();
    }

    @Test
    public void writeHDFSFile() throws IOException
    {
        Path path = new Path("/test/write.txt");
        File file = new File("/home/rocky/myself/test_data/ideaIU-2017.2.5.tar.gz");
        final float fileSize = file.length()/65536;
        FSDataOutputStream fsOut = fs.create(path, new Progressable()
        {
            long fileCount = 0;
            public void progress()
            {
                fileCount ++;
                System.out.println("总进度：" + (fileCount/fileSize)*100 + " %");

            }
        });
        FileInputStream fis = new FileInputStream(file);
     //   IOUtils.copyBytes(fis, fsOut, 4096, false);
        IOUtils.copyBytes(fis, fsOut, 4096);
      //  fsOut.sync();
        fis.close();
        fsOut.close();
    }
    public static void main(String [] args) throws Exception
    {
        ClientJavaOps clientJavaOps = new ClientJavaOps();
        clientJavaOps.init();
        clientJavaOps.writeHDFSFile();
    }
}
