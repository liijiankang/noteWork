package com.ljk;

import com.ljk.modle.DataBaseInfo;
import com.ljk.util.MyDBCPConnectionPool;
import com.ljk.util.SetDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: noteWork
 * @description:
 * @author: jiankang.li@hypers.com
 * @create: 2018-11-22 12:09
 **/
public class MyTest {
    public static void main(String[] args) throws SQLException {
        SetDatabase database = new SetDatabase();
        String path = MyTest.class.getClassLoader().getResource("jdbc.properties").getPath();
        DataBaseInfo dataBaseInfo = database.getBasicInfo("oracle", path);
        MyDBCPConnectionPool pool = new MyDBCPConnectionPool();
        Connection connection = null ;
        PreparedStatement ps = null ;
        String sql = "select * FROM JIANGJIANG_TEST";
        try {
            connection = pool.getConnection(dataBaseInfo);
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString(1));

        }

    }
}
