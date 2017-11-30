package com.itguang.weixinsell.web;

import com.itguang.weixinsell.enums.ResultEnum;
import com.itguang.weixinsell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itguang
 * @create 2017-11-24 17:29
 **/
@RestController
@Slf4j
public class HelloController {




    @RequestMapping("/hello")
    public String hello(){
        log.info("hello...");
        return "hello...";
    }
    @RequestMapping("/test")
    public String test(){

        throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);


    }
}
