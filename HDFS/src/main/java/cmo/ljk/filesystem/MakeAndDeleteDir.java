package cmo.ljk.filesystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * @program: noteWork
 * @description:
 * @author: jiankang.li@hypers.com
 * @create: 2018-12-17 10:10
 **/
public class MakeAndDeleteDir {
    private FileSystem fs;

    public MakeAndDeleteDir() {
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

        conf.addResource(new Path("C:\\Users\\lijk_\\Downloads\\hdfs-site.xml"));
        conf.addResource(new Path("C:\\Users\\lijk_\\Downloads\\core-site.xml"));

        try {
            fs = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean makeDirs(String path){
        Path p = new Path(path);
        System.out.println(p.getParent());
        boolean result=true;
        try {
            if (fs.exists(p)){
                result = fs.delete(p, true);
                result = fs.mkdirs(p);
            }else {
                result = fs.mkdirs(p);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void writeHDFS(){
        Path path = new Path("/test/test/test1.txt");
        try {
            FSDataOutputStream stream = fs.create(path,true,1024);
            stream.write("hello".getBytes());
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void classNameTest(){
        System.out.println(this.getClass().getName());
    }

    public static void main(String[] args) {
        MakeAndDeleteDir test = new MakeAndDeleteDir();
//        test.makeDirs("/test/test/test.txt");
        test.writeHDFS();
//        test.classNameTest();
    }
}
