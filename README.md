### hive相关
##### 分区表的操作
* 创建分区表
   ```
    create table if not exists order_partioned(
        order_id int
        ,order_date string
        ,customer_id int
        ,order_status string
    )
    partitioned by (date_day string)
    row format delimited fields terminated by '|'
    stored as textfile;
  ```
* 查看分区表
  ```SHOW PARTITIONS order_partioned;
     describe formatted order_partioned;
     ```
* 分区表加载数据
```
    load data inpath '/external/order/part-r-00000-f7ab7eec-6a2e-4f6d-8930-8aa9a6163d1a.txt' overwrite into table order_partioned partition(date_day='20180608');
    
    insert overwrite table order_partioned partition(date_day='20140222')
    select order_id
           ,order_date
           ,customer_id
           ,order_status
    from order_partioned
    where date_day='20180608'
    and order_date='2014-02-22 00:00:00'
```
* 外部分区表
```
        create external table if not exists order_partioned_ext(
            order_id int
            ,order_date string
            ,customer_id int
            ,order_status string
        )
        partitioned by (date_day string)
        row format delimited fields terminated by '|'
        stored as textfile;
```
* 分区表加载数据
```
    alter table order_partioned_ext add partition(date_day='20180608') location '/external/order';
```  
* 删除分区
```aidl
    alter table order_partioned_ext drop partition(date_day='20180608')
```
* 动态导入分区表

```
    set hive.exec.dynamic.partition.mode=nonstrict; hive动态分区的一些设置
```
```aidl
    create table if not exists order_partioned_dynamic(
        order_id int
        ,order_date string
        ,customer_id int
        ,order_status string
    )
    partitioned by (date_day string)
    stored as orc;
```
```aidl
    //插入数据
    set hive.exec.max.dynamic.partitions.pernode=200;
    set hive.exec.max.dynamic.partitions=2000;
    insert overwrite table order_partioned_dynamic partition(date_day)
    select order_id
           ,order_date
           ,customer_id
           ,order_status
           ,date_format(order_date,'yyyyMMdd')
    from order_partioned_ext
    where order_date>'2014-01-20 00:00:00'
```
##### 分桶表的操作
* 创建桶表
```aidl
    create table if not exists order_bucketed(
        order_id int
        ,order_date string
        ,customer_id int
        ,order_status string
    )
    clustered by (customer_id) sorted by (order_id) into 4 buckets
    stored as orc;
```
```aidl
    //插入数据
    insert into table order_bucketed
    select order_id
           ,order_date
           ,customer_id
           ,order_status
    from order_partioned_ext
    where date_day='20180608'
```