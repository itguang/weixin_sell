package com.itguang.weixinsell;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author itguang
 * @create 2017-11-24 17:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest2 {



    @Test
    public void test1() {
        String name = "imooc";
        String password = "123456";
        log.debug("debug...");
        log.info("name: " + name + " ,password: " + password);

        //输出变量
        log.info("name: {}, password: {}", name, password);
        log.error("error...");
        log.warn("warn...");
    }


    @Test
    public void test2() {

        log.error("error...");
        log.warn("warn...");
        log.info("info...");
        log.debug("debug...");
        log.trace("trace...");

    }




}
