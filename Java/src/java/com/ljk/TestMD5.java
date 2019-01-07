package com.ljk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: noteWork
 * @description:
 * @author: jiankang.li@hypers.com
 * @create: 2018-12-24 11:52
 **/
public class TestMD5 {

    public static void testMD5(){
        byte[] bytes = new byte[2048];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("C:\\A-MyWork\\GitR\\git\\noteWork\\Java\\src\\main\\test.txt");
            fileInputStream.read(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = new String(bytes);
        String[] splits = s.split("\r\n");
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[][] digest = new byte[4][];
        for (int i = 0; i < 4; i++) {
            digest[i] = md5.digest(splits[i].getBytes());
        }
        for (byte[] b : digest) {
            System.out.println(new String(b));
        }

    }

    public static void main(String[] args) {
        testMD5();
    }
}
