package com.ljk.testMapPartition;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.hive.HiveContext;
import scala.Serializable;
import scala.Tuple2;

import java.util.*;


/**
 * @program: noteWork
 * @description: 测试Java代码中mappartition的用法
 * @author: jiankang.li@hypers.com
 * @create: 2018-12-12 16:24
 **/
public class TestSpark implements Serializable {

    private static SparkConf sparkConf = new SparkConf().setAppName("TestSpark").setMaster("local[*]");
    private static final JavaSparkContext ctx = new JavaSparkContext(sparkConf);


    /**
     *测试wordcount
     */
    public void testWordCount(){
        final JavaRDD<String> lines = ctx.textFile("C:\\Users\\lijk_\\Desktop\\properties.txt", 1);
        lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String s) throws Exception {
                return Arrays.asList(s.split("\\."));
            }
        }).mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String,Integer>(s,1);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return new Integer(integer.intValue()+integer2.intValue());
            }
        }).foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                System.out.println(stringIntegerTuple2._1+":"+stringIntegerTuple2._2);
            }
        });
    }

    /**
     * 测试mapPartition
     */
    public void testMapPartitions(){
        final JavaRDD<String> lines = ctx.textFile("C:\\Users\\lijk_\\Desktop\\properties.txt", 3);
        lines.mapPartitions(new FlatMapFunction<Iterator<String>, String>() {
            List list = new ArrayList();
            @Override
            public Iterable<String> call(Iterator<String> stringIterator) throws Exception {
                while (stringIterator.hasNext()){
                    String[] split = stringIterator.next().split("\\.");
                    for (String s : split) {
                        list.add(s);
                    }
                }
                return list;
            }
        }).saveAsTextFile("C:\\Users\\lijk_\\Desktop\\test.txt");
    }

    public void testDataFrame(){
        SQLContext sqlContext = new SQLContext(ctx);
        Properties properties = new Properties();
        properties.setProperty("url","jdbc:mysql://10.88.88.201:3306/test");
        properties.setProperty("driver","org.mariadb.jdbc.Driver");
        properties.setProperty("user","test");
        properties.setProperty("password","123456");
        DataFrame dataFrame = sqlContext.read().jdbc("jdbc:mysql://10.88.88.201:3306/test", "jiangjiang_test", properties);
        dataFrame.show();

        dataFrame.write().mode(SaveMode.Append).jdbc("jdbc:mysql://10.88.88.201:3306/test","test_ljktest",properties);
    }



    public static void main(String[] args) {
        TestSpark test = new TestSpark();
        //test.testWordCount();
//        test.testMapPartitions();
        test.testDataFrame();

    }
}
