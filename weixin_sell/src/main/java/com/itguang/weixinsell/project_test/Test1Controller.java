package com.itguang.weixinsell.project_test;

import com.itguang.weixinsell.project_test.enums.MyResultEnum;
import com.itguang.weixinsell.project_test.exception.MyException;
import com.itguang.weixinsell.project_test.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统一异常处理
 *
 * @author itguang
 * @create 2017-12-02 15:09
 **/
@RestController
@Slf4j
public class Test1Controller {


    @RequestMapping("/test1/{username}")
    public Result test2(@PathVariable(value = "username") String username){

        log.info("username={}",username);

        if(!"root".equals(username)){
                throw new MyException(MyResultEnum.FAIL);
        }

        return new Result(MyResultEnum.SUCCESS);

    }




}
