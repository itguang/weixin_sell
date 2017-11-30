package com.itguang.weixinsell.web;

import com.itguang.weixinsell.enums.ResultEnum;
import com.itguang.weixinsell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.net.URLEncoder.encode;

/**
 * 微信授权Controller
 *
 * @author itguang
 * @create 2017-11-30 10:04
 **/
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;


    /**
     * 微信点餐系统 认证 url
     * @param returnUrl
     * @return
     */
    @RequestMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){

        log.info("returnUrl={}",returnUrl);


        //1.构造网页授权url()
        String redirectURI = "http://sell.com/sell/wechat/userInfo";
        String url = wxMpService.oauth2buildAuthorizationUrl(redirectURI, WxConsts.OAUTH2_SCOPE_USER_INFO, encode(returnUrl));

        log.info("url={}",url);
        return "redirect:"+url;
    }

    /**
     * 微信网页授权 回调 url
     * @param code
     * @param returnUrl
     * @return
     */
    @RequestMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl){

        log.info("code={},returnUrl={}",code,returnUrl);

        //2.获得access token()
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("[微信网页授权] 授权失败:={}",e.getError().getErrorMsg());
            throw new SellException(ResultEnum.WECGAT_ERROR);
        }

        //3.获得用户基本信息
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("openId={}",openId);


        return "redirect:"+returnUrl+"?openId="+openId;
    }

    @RequestMapping("/test")
    public void test(){

        log.info("test...");

    }


}
