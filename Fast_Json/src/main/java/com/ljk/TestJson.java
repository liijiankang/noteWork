package com.ljk;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.*;

/**
 * @program: noteWork
 * @description: 测试一下阿里的json转换方法
 * @author: ljk
 * @create: 2018-11-11 16:48
 **/
public class TestJson {
    /**
     * 利用gson解析json格式的文件
     * @throws IOException
     */
    public static void testCommonJson() throws IOException {
        JsonParser parser = new JsonParser();
        FileReader json = new FileReader("C:\\A-MyWork\\GitR\\git\\noteWork\\Fast_Json\\src\\main\\java\\com\\ljk\\test.json");
        JsonObject parse = (JsonObject)parser.parse(json);
        System.out.println(parse.get("cat").getAsString());
        System.out.println(parse.get("pop").getAsBoolean());
        JsonArray language = parse.getAsJsonArray("language");
        for (int i = 0 ;i<language.size();i++){
            JsonObject json1 = language.get(i).getAsJsonObject();
            System.out.print(json1.get("id").getAsInt()+" ");
            System.out.print(json1.get("ide").getAsString()+" ");
            System.out.print(json1.get("name").getAsString()+" ");
        }
    }

    /**
     * json转换为Java对象
     */
    public static void testJsonToJava(){
        JsonObject jObj = new JsonObject();
        jObj.addProperty("name","json");


    }

    /**
     *测试对象
     */
    public static class  Apple{
        String name;
        String price;

//        public Apple(String name, String price) {
//            this.name = name;
//            this.price = price;
//        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 测试阿里json工具,将apple对象转换为json格式的数据
     */
    public static void testAlibabaJsonUtil(){
        Apple apple = new Apple();
        apple.setName("apple");
        apple.setPrice("price");
        //对象转换为json
        JSONObject json = (JSONObject) JSONObject.toJSON(apple);
        System.out.println(json.get("name"));
        System.out.println(json.get("price"));
        //json转换为字符串
        String s = JSONObject.toJSONString(json);
        System.out.println(s);

    }

    public  Map<String,ArrayList<String>> testJson() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\A-MyWork\\GitR\\git\\noteWork\\Fast_Json\\src\\main\\java\\com\\ljk\\test1.json");
        byte[] bytes = new byte[2048];
        int read = fileInputStream.read(bytes);
        fileInputStream.close();
        String json = new String(bytes);
        String key = null;
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        JSONArray parse = (JSONArray)JSONArray.parse(json);
        for (int i = 0; i < parse.size(); i++) {
            JSONObject jsonObject = parse.getJSONObject(i);
            System.out.println(jsonObject);
            Iterator<String> it = jsonObject.keySet().iterator();
            while (it.hasNext()){
                ArrayList<String> list = new ArrayList<>();
                key = it.next();
                if (jsonObject.get(key) instanceof String){
                    list.add((String)jsonObject.get(key));
                }
                if (key != null && map.containsKey(key)){
                    map.get(key).addAll(list);
                }else if (key != null && !map.containsKey(key)){
                    map.put(key,list);
                }
            }
        }
        System.out.println();
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
        }

        return map;
    }


    public static void main(String[] args) throws IOException {
        TestJson test = new TestJson();
//        testCommonJson();
//        testAlibabaJsonUtil();
        Map<String, ArrayList<String>> map = test.testJson();


    }
}
