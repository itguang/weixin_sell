package com.itguang.weixinsell.project_test;

import com.itguang.weixinsell.entity.ProductCategoryEntity;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 探究Class类
 *
 * @author itguang
 * @create 2017-12-02 16:19
 **/


public class TestClass {





    @Test
    public void test(){
        ProductCategoryEntity categoryEntity = new ProductCategoryEntity();
        categoryEntity.setCategoryType(1);
        categoryEntity.setCategoryName("数码产品");

        Class<? extends ProductCategoryEntity> clazz = categoryEntity.getClass();

        //我们可以通过它来获得具体一个类的域/方法和构造器

        Package aPackage = clazz.getPackage();
        System.out.println("获取包名+类名="+aPackage);


        String clazzSimpleName = clazz.getSimpleName();
        System.out.println("只获取类名:="+clazzSimpleName);

        Field[] fields = clazz.getFields();
        System.out.println("返回字段数组:="+fields.length);


    }



    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        我们可以通过一个类来获取Class对象，进而获取此类的信息。也可以通过全类名来获取Class对象。

        Class cl = Class.forName("java.lang.String");
        System.out.println(cl.getPackage());
//        有了这个类的Class对象，我们就可以创建这个类的对象。最方便/快速的方法是调用newInstance()。默认情况下，它默认调用无参构造来返回一个对象。

        String str = (String) cl.newInstance();
    }





}
