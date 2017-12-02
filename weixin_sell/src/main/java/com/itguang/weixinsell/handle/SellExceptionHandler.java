package com.itguang.weixinsell.handle;

import com.itguang.weixinsell.config.ProjectUrlConfig;
import com.itguang.weixinsell.exception.SellerAuthorizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 身份异常统一异常处理
 *
 * @author itguang
 * @create 2017-12-01 16:13
 **/
@ControllerAdvice
@Slf4j
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;


//    @ExceptionHandler(value = SellerAuthorizeException.class)
//    public ModelAndView handlerAuthorizeException() {
//
//        log.info("SellerAuthorizeException 统一异常处理...");
//
//        //身份验证出现异常,统一重定向到 授权页面
//        ModelAndView modelAndView = new ModelAndView("redirect:" + projectUrlConfig.getDomain() + "authorize/login");
//        return modelAndView;
//
//    }


}
