package com.itguang.weixinsell.project_test;

import com.itguang.weixinsell.project_test.enums.MyResultEnum;
import com.itguang.weixinsell.project_test.pojo.SellerInfoEntity;
import com.itguang.weixinsell.project_test.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itguang
 * @create 2017-12-03 12:32
 **/
@RestController
@RequestMapping("/jackson")
public class TestJackson {

    @RequestMapping("test1")
    public Result test1(){

        SellerInfoEntity entity = new SellerInfoEntity("1","user1","","openid");

        return new Result(MyResultEnum.SUCCESS,entity);

    }

}
