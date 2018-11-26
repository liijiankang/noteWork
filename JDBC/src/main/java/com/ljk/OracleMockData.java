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
 * @create: 2018-11-23 15:25
 **/
public class OracleMockData {
    public static void main(String[] args) throws SQLException {
        String path = OracleMockData.class.getClassLoader().getResource("jdbc.properties").getPath();
        SetDatabase setDatabase = new SetDatabase();
        DataBaseInfo oracleInfo = setDatabase.getBasicInfo("oracle", path);
        MyDBCPConnectionPool pool = new MyDBCPConnectionPool();
        Connection connection = pool.getConnection(oracleInfo);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<Date, String> data = MockMysql.MockData();
        for (Map.Entry<Date,String> dateStringEntry : data.entrySet()) {
            String date = format.format(dateStringEntry.getKey());
            String flag = dateStringEntry.getValue();
            String sql = "INSERT INTO  CALSERVICE VALUES (to_date('" + date + "','yyyy-MM-dd'),'" + flag + "');";
            System.out.println(sql);
        }


    }
}