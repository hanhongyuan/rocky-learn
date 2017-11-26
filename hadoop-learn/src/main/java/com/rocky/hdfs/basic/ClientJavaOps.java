package com.rocky.hdfs.basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
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


    /**
     * hdfs 获取指定路径下的文件或文件夹的状态
     * @throws IOException
     */
    @Test
    public void testListStatus() throws IOException
    {
        for (FileStatus fileStatus : fs.listStatus(new Path("")))
        {
            System.out.println(fileStatus.getPath().getName());
            System.out.println(fileStatus.isDirectory() ? "directory..." : "file");
        }
    }
    /**
     * hdfs 递归列出指定目录下的所有文件属性
     * @throws IOException
     */
    @Test
    public void testListFiles() throws IOException
    {
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path(""), true);

        while (listFiles.hasNext()){
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println(fileStatus.getBlockLocations());
            for (BlockLocation blockLocation : fileStatus.getBlockLocations())
            {
                System.out.println(blockLocation.getOffset());
                System.out.println(blockLocation.getHosts());
                System.out.println(blockLocation.getLength());
                System.out.println(blockLocation.getNames());
            }
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getReplication());
            System.out.println(fileStatus.getOwner());
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getPath().getName());
            System.out.println("----------------------------------");
        }
    }
    /**
     * hdfs测试删除文件或目录
     * @throws IOException
     */
    @Test
    public void testDelete() throws IOException
    {
        Path path = new Path("");
        //参数二：表示是否递归删除
        boolean isSuccess = fs.delete(path, true);
        System.out.println(isSuccess == true ? "删除成功..." : "删除失败");
    }
    /**
     * Hdfs 创建目录测试
     * @throws IOException
     */
    @Test
    public void testMkdir() throws IOException
    {
        Path path = new Path("");

        boolean isSuccess = fs.mkdirs(path);
        System.out.println(isSuccess == true ? "创建成功..." : "创建失败...");
    }

    /**
     * hdfs 下载文件测试--可以下载指定文件的指定大小的下载
     * @throws IOException
     */
    @Test
    public void readHDFSFile() throws IOException
    {
        Path path = new Path("/test/test.txt");
        FSDataInputStream fsIn = fs.open(path);
        IOUtils.copyBytes(fsIn, System.out, 4096, false);
        fsIn.close();
    }

    /**
     * hdfs 指定文件随机下载--从文件1k处开始下载，下载1K
     * @throws IOException
     */
    @Test
    public void testRandomAccessReadHDFSFile() throws IOException
    {
        Path path = new Path("/test/test.txt");
        FSDataInputStream fsIn = fs.open(path);
        fsIn.seek(1 * 1024);
       IOUtils.copyBytes(fsIn, System.out,  1 * 1024L, false);
        fsIn.close();
    }

    /**
     * hdfs 上传文件测试--可以指定上传指定文件的指定大小的文件上传
     * @throws IOException
     */
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
        //上传指定文件的10M
      //  IOUtils.copyBytes(fis, fsOut, 10 * 1024 * 1024);
        IOUtils.copyBytes(fis, fsOut, 4096);
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
