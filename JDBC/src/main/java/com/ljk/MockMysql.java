package com.ljk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * @program: noteWork
 * @description: 测试日历服务
 * @author: jiankang.li@hypers.com
 * @create: 2018-11-21 14:20
 **/
public class MockMysql {

    /**
     * 获取数据库信息
     * @return
     */
    public static DatabaseInfo getDataBaseInfo(String path,String style){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("mysql".equals(style)){
            String url = properties.getProperty("mysql.url");
            String driver = properties.getProperty("mysql.driver");
            String user = properties.getProperty("mysql.user");
            String password = properties.getProperty("mysql.password");
            return new DatabaseInfo(url,driver,user,password) ;
        }
        if ("oracle".equals(style)){
            String url = properties.getProperty("oracle.url");
            String driver = properties.getProperty("oracle.driver");
            String user = properties.getProperty("oracle.user");
            String password = properties.getProperty("oracle.password");
            return new DatabaseInfo(url,driver,user,password) ;
        }
        if ("sqlserver".equals(style)){
            String url = properties.getProperty("sqlserver.url");
            String driver = properties.getProperty("sqlserver.driver");
            String user = properties.getProperty("sqlserver.user");
            String password = properties.getProperty("sqlserver.password");
            return new DatabaseInfo(url,driver,user,password) ;
        }
        return null;
    }
    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(DatabaseInfo info){
        Connection connection = null;
        try {
            Class.forName(info.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(info.getUrl(),info.getUser(),info.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 模拟数据
     */
    public static HashMap<Date,String> MockData(){
        Random random = new Random();
        Date date = null ;
        String flag = null;
        HashMap<Date, String> map = new HashMap<Date,String>() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(2018,1,1);
        long start = cal.getTimeInMillis();
        cal.set(2018,12,31);
        long end = cal.getTimeInMillis();
        for (int i=0;i<100;i++){
            date = new Date(start + (long) (random.nextDouble() * (end - start)));
            cal.setTime(date);
            if (cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                flag = "workday";
            }else {
                flag = "weekend" ;
            }
            map.put(date,flag);
        }
        return map;
    }

    /**
     * 建表
     */
    public static void createTable(String url,Connection connection) throws SQLException {
        boolean e=false;
        if (url.contains("mysql")){
            String sql = "CREATE TABLE if not exists calSerrvice\n" +
                    "(\n" +
                    "    workdate VARCHAR(10),\n" +
                    "    flag VARCHAR(10)\n" +
                    ");";
            PreparedStatement ps = connection.prepareStatement(sql);
            e = ps.execute();
        }else if (url.contains("oracle")){
            String sql = "CREATE TABLE if not exists calSerrvice\n" +
                    "(\n" +
                    "    workdate VARCHAR(10),\n" +
                    "    flag VARCHAR(10)\n" +
                    ");";
            PreparedStatement ps = connection.prepareStatement(sql);
            e = ps.execute();

        }else if (url.contains("sqlserver")){
            String sql = "CREATE TABLE if not exists kylo.dbo.calSerrvice\n" +
                    "(\n" +
                    "    workdate VARCHAR(10),\n" +
                    "    flag VARCHAR(10)\n" +
                    ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            e = ps.execute();
        }
    }

    /**
     * 向数据库导入数据
     * @param map
     */
    public static void saveData(HashMap<Date,String> map,String url,Connection connection) throws SQLException {


        for (Map.Entry<Date,String> a : map.entrySet()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("INSERT INTO calSerrvice VALUES(");
            buffer.append("'");
            buffer.append(a.getKey());
            buffer.append("'");
            buffer.append(",");
            buffer.append("'");
            buffer.append(a.getValue());
            buffer.append("'");
            buffer.append(" )");
            System.out.println(buffer.toString());
            PreparedStatement ps = connection.prepareStatement(buffer.toString());
            ps.execute();
        }

    }

    /**
     * 测试数据库连接
     * @param connection
     * @throws SQLException
     */
    public static void testConnection(Connection connection) throws SQLException {
        ResultSet rs = null ;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select * from Student");
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs != null){
            while (rs.next()){
                System.out.print(rs.getString(1));
                System.out.print("  ");
                System.out.print(rs.getString(2));
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        String path = MockMysql.class.getClassLoader().getResource("jdbc.properties").getPath();
        String path1 = System.getProperty("user.dir");
        HashMap<Date,String> map = MockData();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//        DatabaseInfo mysqlInfo = getDataBaseInfo(path,"mysql");
//        Connection mysqlConnection = getConnection(mysqlInfo);
//        createTable(mysqlInfo.getUrl(),mysqlConnection);
//        saveData(map,mysqlInfo.getUrl(),mysqlConnection);
//        mysqlConnection.close();

        /**
         * oracle见表
         */
//        DatabaseInfo oracleInfo = getDataBaseInfo(path,"oracle");
//        Connection oracleConnection = getConnection(oracleInfo);
//        createTable(oracleInfo.getUrl(),oracleConnection);
//        oracleConnection.close();
//
        /**
         * sqlserver见表
         */
//        DatabaseInfo sqlserverInfo = getDataBaseInfo(path,"sqlserver");
//        Connection sqlserverConnection = getConnection(sqlserverInfo);
//        createTable(sqlserverInfo.getUrl(),sqlserverConnection);
//        sqlserverConnection.close();


    }

    /**
     * 数据库连接信息
     */
    public static class DatabaseInfo {
        private String url;
        private String driver;
        private String user;
        private String password;

        public DatabaseInfo(String url, String driver, String user, String password) {
            this.url = url;
            this.driver = driver;
            this.user = user;
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
