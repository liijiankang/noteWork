package cmo.ljk.filesystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @program: noteWork
 * @description:
 * @author: jiankang.li@hypers.com
 * @create: 2018-12-19 10:37
 **/
public class ReadFromHdfs {

    Configuration configuration = null;

    /**
     *初始化hadoop环境
     */
    public ReadFromHdfs() {
        configuration = new Configuration();
        configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        configuration.addResource(new Path("C:\\Users\\lijk_\\Downloads\\hdfs-site.xml"));
        configuration.addResource(new Path("C:\\Users\\lijk_\\Downloads\\core-site.xml"));

    }

    /**
     * kerberos安全认证
     */
    public void kerberosRegist(){

        //配置Hadoop的Kerberos认证)
        configuration.setBoolean("hadoop.security.authorization", true);
        configuration.set("hadoop.security.authentication", "Kerberos");
        //krb5.conf在windows本地
        System.setProperty("java.security.krb5.conf", "C:\\Users\\lijk_\\Downloads\\krb5.conf");
        UserGroupInformation.setConfiguration(configuration);
        try {
            UserGroupInformation.loginUserFromKeytab("kylo@ABC123.WU", "C:\\Users\\lijk_\\Downloads\\kylo.keytab");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从hdfs上读取文件
     */
    public void read(String path){
        FileSystem fs = null;
        FSDataInputStream stream = null;
        try {
            fs = FileSystem.get(configuration);
            stream = fs.open(new Path(path));
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\lijk_\\Downloads\\beidou_field_policy.json"));
            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while(line != null) {
                sb.append(line);
                line = br.readLine();
            }
            System.out.println(sb.toString());
            while (stream.available()>0){
                System.out.println(stream.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReadFromHdfs test = new ReadFromHdfs();
        test.kerberosRegist();
        test.read("/tmp/beidou_field_policy.json");
    }
}
