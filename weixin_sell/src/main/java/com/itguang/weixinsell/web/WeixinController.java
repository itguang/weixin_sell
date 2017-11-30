package com.itguang.weixinsell.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author itguang
 * @create 2017-11-30 9:25
 **/
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {




    @RequestMapping("/test")
    public void test(@RequestParam("code") String code) {
        log.info("hello weixin...");

        log.info("code={}",code);

        //得到code后,访问此地址,即可获取用户信息(通过code换取网页授权access_token)
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1642c5a5400c82e2&secret=b29ce763b313b406ff9d8f96cf3fb6e0&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}",response);


    }

}
