package com.ljk;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.addResource("C:\\Users\\lijk_\\Downloads\\core-site.xml");
        conf.addResource("C:\\Users\\lijk_\\Downloads\\hdfs-site.xml");
        try {
            FileSystem fileSystem = FileSystem.get(conf);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
