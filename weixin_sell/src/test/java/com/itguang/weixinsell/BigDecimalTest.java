package com.itguang.weixinsell;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author itguang
 * @create 2017-11-26 9:50
 **/
public class BigDecimalTest {


    @Test
    public void test1() {
        System.out.println(0.06 + 0.01);
        System.out.println(1.0 - 0.42);
        System.out.println(4.015 * 100);
        System.out.println(303.1 / 1000);

    }



    @Test
    public void test2() {
        BigDecimal b1 = new BigDecimal(Double.toString(0.06));
        System.out.println(b1);

        BigDecimal b2 = BigDecimal.valueOf(0.48);
        System.out.println(b2);


    }
    @Test
    public void test3() {
        BigDecimal b1 = new BigDecimal(Double.toString(0.006));

        BigDecimal b2 = BigDecimal.valueOf(0.01);
        BigDecimal b3 = b1.add(b2);//b1+b2=b3
        System.out.println("b3 = "+b3);
        double doubleValue = b3.doubleValue();
        System.out.println("doubleValue = "+doubleValue);

        float floatValue = b3.floatValue();
        System.out.println("floatValue = "+floatValue);


    }
}
