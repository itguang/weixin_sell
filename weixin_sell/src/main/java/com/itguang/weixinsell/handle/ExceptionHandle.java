package com.itguang.weixinsell.handle;

import com.itguang.weixinsell.VO.ResultOV;
import com.itguang.weixinsell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author itguang
 * @create 2017-11-28 14:00
 **/
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultOV handle(SellException e){
        log.error("SellException="+e.getCode()+":"+e.getMessage());
        return new ResultOV(e.getCode(),e.getMessage());

    }
}
