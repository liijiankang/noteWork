package com.ljk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException {
        Random random = new Random();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null ;
        cal.set(2018,1,1);
        long start = cal.getTimeInMillis();
        cal.set(2018,12,31);
        long end = cal.getTimeInMillis();
        date = new Date(start + (long) (random.nextDouble() * (end - start)));
        cal.setTime(date);

        System.out.println();
    }
}
