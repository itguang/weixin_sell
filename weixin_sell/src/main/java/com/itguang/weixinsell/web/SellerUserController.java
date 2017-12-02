package com.itguang.weixinsell.web;

import com.itguang.weixinsell.config.ProjectUrlConfig;
import com.itguang.weixinsell.constant.CookieConstant;
import com.itguang.weixinsell.constant.RedisConstant;
import com.itguang.weixinsell.service.impl.SellerInfoServiceImpl;
import com.itguang.weixinsell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author itguang
 * @create 2017-12-01 11:02
 **/
@Controller
public class SellerUserController {

    @Autowired
    private SellerInfoServiceImpl sellerInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;


    /**
     * 分布式session登陆:
     * 第一步:获取openid.
     * 第二步:生成token,保存到redis
     * 第三步:把生成的token保存到Cookie
     * <p>
     * 即:客户端可以根据此 token ,换取 redis 中的 openid 等信息
     *
     * @param openid
     * @param response
     *
     *  http://sell.com/sell/login?openid=123
     */
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid, HttpServletResponse response) {


        // 1.设置 token 至 Redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        //2.设置 token 至 Cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

        //重定向时要使用 绝对路径
        ModelAndView modelAndView = new ModelAndView("redirect:" + projectUrlConfig.getDomain() + "/sell/seller/order/list");

        return modelAndView;


    }

    /**
     * 登出操作
     *
     * 第一步: 删除 redis 中的token数据
     *
     * 第二步: 删除 Cookie 中的token数据
     *
     * @param response
     * @param request
     * @param openid
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout (HttpServletResponse response, HttpServletRequest request,@RequestParam("openid") String openid){

        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String redisKey = cookie.getValue();
        //删除 Rerdis 中的token
        redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, redisKey));
        //删除Cookie中的token
        CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        //重定向
        ModelAndView modelAndView = new ModelAndView("redirect:"+projectUrlConfig.getDomain()+"sell/seller/order/list");
        return modelAndView;

    }


}
