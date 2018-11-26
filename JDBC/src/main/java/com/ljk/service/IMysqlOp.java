package com.ljk.service;

/**
 * @program: noteWork
 * @description: mysql数据库操作
 * @author: jiankang.li@hypers.com
 * @create: 2018-11-22 17:13
 **/
public interface IMysqlOp {
    //建表
    void createTable();
    //插入数据
    void insertData();
    //删除数据
    void dropData();
    //更新数据
    void updateData();
    //查询
    void queryData();
}
