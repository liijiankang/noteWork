package com.ljk.util;

import com.ljk.modle.DataBaseInfo;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: noteWork
 * @description: 获取数据库连接
 * @author: jiankang.li@hypers.com
 * @create: 2018-11-22 10:39
 **/
public class MyDBCPConnectionPool {
    public Connection getConnection(DataBaseInfo info) throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(info.getDriver());
        dataSource.setUsername(info.getUser());
        dataSource.setUrl(info.getUrl());
        dataSource.setPassword(info.getPassword());
        return dataSource.getConnection();
    }
}
