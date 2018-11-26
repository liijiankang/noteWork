package com.ljk;

import com.ljk.modle.DataBaseInfo;
import com.ljk.util.MyDBCPConnectionPool;
import com.ljk.util.SetDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: noteWork
 * @description:
 * @author: jiankang.li@hypers.com
 * @create: 2018-11-23 17:06
 **/
public class SqlServerMock {
    public static void main(String[] args) throws SQLException {


        String path = OracleMockData.class.getClassLoader().getResource("jdbc.properties").getPath();
        SetDatabase setDatabase = new SetDatabase();
        DataBaseInfo oracleInfo = setDatabase.getBasicInfo("sqlserver", path);
        MyDBCPConnectionPool pool = new MyDBCPConnectionPool();
        Connection connection = pool.getConnection(oracleInfo);
//        String sql = "CREATE TABLE calSerrvice(workdate DATE ,flag VARCHAR (10))";
//        PreparedStatement ps = connection.prepareStatement(sql);
//        ps.execute();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<Date, String> data = MockMysql.MockData();
        for (Map.Entry<Date,String> dateStringEntry : data.entrySet()) {
            String date = format.format(dateStringEntry.getKey());
            String flag = dateStringEntry.getValue();
            String sql1 = "INSERT INTO  calSerrvice VALUES ('" + date + "','" + flag + "');";
            System.out.println(sql1);
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.execute();
        }
    }

}
