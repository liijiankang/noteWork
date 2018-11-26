package com.ljk.jdbcconnection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.tools.ant.taskdefs.LoadProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @program: noteWork
 * @description: 第一次使用
 * @author: jiankang.li@hypers.com
 * @create: 2018-11-19 10:03
 **/
public class TestOracle {

    /**
     * 获取Oracle连接
     * @return
     */
    public  Connection getConnectionWithBasicDataSource(){
        Logger logger = LoggerFactory.getLogger(TestOracle.class);
        Connection connection = null;
        Properties properties = new Properties();
        try {
            //这种方法加载的话该文件必须放到resouces目录下
//            properties.load(TestOracle.class.getClassLoader().getResourceAsStream("jdbc2.properties"));
//            properties.load(hiveJDBC.class.getClassLoader().getResourceAsStream("jdbc.properties"));
//            properties.load(LoadProperties.class.getResourceAsStream("jdbc.properties"));
//            String resource = TestOracle.class.getClassLoader().getResource("jdbc.properties").getPath();
            String resource = this.getClass().getClassLoader().getResource("jdbc.properties").getPath();

            FileInputStream fi = new FileInputStream(new File("resource"));
            properties.load(fi);
        } catch (IOException e) {
            logger.error("获取jdbc的properties错误");
            e.printStackTrace();
        }
        BasicDataSource bds = new BasicDataSource();
        System.out.println(properties.getProperty("oracle.driver"));
        System.out.println(properties.getProperty("oracle.url"));
        bds.setDriverClassName(properties.getProperty("oracle.driver"));
        bds.setUrl(properties.getProperty("oracle.url"));
        bds.setPassword(properties.getProperty("oracle.password"));
        bds.setUsername(properties.getProperty("oracle.user"));
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

    public static void main(String[] args) {
        TestOracle test = new TestOracle();
        String path = TestOracle.class.getClassLoader().getResource("").getPath();
        String path1 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(path1);
        System.out.println(path);
//        test.getConnectionWithBasicDataSource();
    }
}
