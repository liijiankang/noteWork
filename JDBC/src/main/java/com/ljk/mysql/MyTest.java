package com.ljk.mysql;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MyTest {
    public static void config(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\my_work\\gitProject\\noteWork\\JDBC\\src\\main\\resources\\jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = properties.getProperty("mysql.driver");
        String url = properties.getProperty("mysql.url");
        String user = properties.getProperty("mysql.user");
        String password = properties.getProperty("mysql.password");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUrl(url);
        System.out.println(url);
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "select * from Student";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)
                +" "+resultSet.getString(3)+" "+resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        config();
    }
}
