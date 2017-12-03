package com.itguang.weixinsell.project_test.handle;

import com.itguang.weixinsell.project_test.exception.MyException;
import com.itguang.weixinsell.project_test.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author itguang
 * @create 2017-12-02 15:34
 **/

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result handleMyException(HttpServletRequest request, MyException e){
        String message = e.getMessage();
        Integer code = e.getCode();

        Result result = new Result(code,message);

        return result;


    }



}
