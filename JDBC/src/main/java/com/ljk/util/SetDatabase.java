package com.ljk.util;

import com.ljk.modle.DATABASE_STYLE;
import com.ljk.modle.DataBaseInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @program: noteWork
 * @description: 设置数据库配置信息
 * @author: jiankang.li@hypers.com
 * @create: 2018-11-22 16:11
 **/
public class SetDatabase {

    /**
     * 获取不同数据库基本信息
     * @param databaseStyle
     * @param path
     * @return
     */
    public DataBaseInfo getBasicInfo(String databaseStyle,String path){
        DataBaseInfo info = new DataBaseInfo();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DATABASE_STYLE style = getStyle(databaseStyle);
        switch (style){
            case MYSQL:{
                info.setUrl(properties.getProperty("mysql.url"));
                info.setDriver(properties.getProperty("mysql.driver"));
                info.setUser(properties.getProperty("mysql.user"));
                info.setPassword(properties.getProperty("mysql.password"));
                break;
            }
            case ORACLE:{
                info.setUrl(properties.getProperty("oracle.url"));
                info.setDriver(properties.getProperty("oracle.driver"));
                info.setUser(properties.getProperty("oracle.user"));
                info.setPassword(properties.getProperty("oracle.password"));
                break;
            }
            case SQLSERVER:{
                info.setUrl(properties.getProperty("sqlserver.url"));
                info.setDriver(properties.getProperty("sqlserver.driver"));
                info.setUser(properties.getProperty("sqlserver.user"));
                info.setPassword(properties.getProperty("sqlserver.password"));
                break;
            }
        }

        info.setMaxWaitMillis(properties.getProperty("maxWaitMillis"));
        info.setInitialSize(properties.getProperty("initialSize"));
        info.setMaxTotal(properties.getProperty("maxTotal"));
        info.setMaxIdle(properties.getProperty("maxIdle"));
        info.setMinIdle(properties.getProperty("minIdle"));
        info.setRemoveAbandonedOnBorrow(properties.getProperty("removeAbandonedOnBorrow"));
        info.setRemoveAbandonedTimeout(properties.getProperty("removeAbandonedTimeout"));
        return info;
    }

    /**
     * 解析数据库类型
     * @param style
     * @return
     */
    private DATABASE_STYLE getStyle(String style){
        if (style.toLowerCase().contains("mysql")){
            return DATABASE_STYLE.MYSQL;
        }else if (style.toLowerCase().contains("oracle")){
            return DATABASE_STYLE.ORACLE;
        }else if (style.toLowerCase().contains("sqlserver")){
            return  DATABASE_STYLE.SQLSERVER;
        }
        return null;
    }

}
