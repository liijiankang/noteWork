package com.ljk.jdbcconnection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @program: noteWork
 * @description:
 * @author: ljk
 * @create: 2018-11-09 15:08
 **/
public class hiveJDBC {
    /**
     * 通过basicdatasorce获取jdbc的connection
     * @return
     */
    public Connection getConnectionWithBasicDataSource(){
        Logger logger = LoggerFactory.getLogger(hiveJDBC.class);
        Connection connection = null;
        Properties properties = new Properties();
        try {
            //这种方法加载的话该文件必须放到resouces目录下
            properties.load(hiveJDBC.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            logger.error("获取jdbc的properties错误");
            e.printStackTrace();
        }
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(properties.getProperty("jdbc.driver"));
        bds.setUrl(properties.getProperty("jdbc.url"));
        bds.setPassword(properties.getProperty("jdbc.password"));
        bds.setUsername(properties.getProperty("jdbc.user"));
        try {
            connection = bds.getConnection();
        } catch (SQLException e) {
            logger.error("获取jdbc的connection错误");
            e.printStackTrace();
        }
        if (null != connection){
            return connection;
        }else return null;
    }

    /**
     * 通过DriverManager获取connection
     * @return
     */
    public Connection getConnectionWithDriveManager(){
        Logger logger = LoggerFactory.getLogger(hiveJDBC.class);
        Connection connection = null;
        Properties properties = new Properties();
        try {
            properties.load(hiveJDBC.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            logger.error("获取jdbc的properties错误");
            e.printStackTrace();
        }
        try {
            Class.forName(properties.getProperty("hive.driver"));
        } catch (ClassNotFoundException e) {
            logger.error("加载驱动失败");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(properties.getProperty("hive.url"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 添加了Kerberos认证服务的jdbc
     * @return
     */
    public Connection getConnectionWithKerberos(){
        //hadoop的configuration
        Configuration configuration = new Configuration();
        //配置Hadoop的Kerberos认证
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
//        return getConnectionWithBasicDataSource();
        return getConnectionWithDriveManager();
    }

    /**
     * 测试getConnection
     * @throws SQLException
     */
    public void test1() throws SQLException {
//        Connection connection = getConnectionWithBasicDataSource();
//        Connection connection = getConnectionWithDriveManager();
        Connection connection = getConnectionWithKerberos();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
             ps = connection.prepareStatement("show databases");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (rs.next()){
            String r = rs.getString(1);
            System.out.println(r);
        }
    }


    public static void main(String[] args) throws SQLException {

        hiveJDBC hiveJdbc = new hiveJDBC();
        hiveJdbc.test1();
    }
}
