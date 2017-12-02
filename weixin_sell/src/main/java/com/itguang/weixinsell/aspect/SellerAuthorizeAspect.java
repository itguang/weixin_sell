package com.itguang.weixinsell.aspect;

import com.itguang.weixinsell.constant.CookieConstant;
import com.itguang.weixinsell.constant.RedisConstant;
import com.itguang.weixinsell.exception.SellerAuthorizeException;
import com.itguang.weixinsell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author itguang
 * @create 2017-12-01 15:37
 **/
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Pointcut("execution(public * com.itguang.weixinsell.web.Seller*.*(..))" +
            "&& !execution(public * com.itguang.weixinsell.web.SellerUserController.*(..))")
    public void verify() {
    }




    @org.aspectj.lang.annotation.Before("verify()")
    public void doVerify() {

//        //在Spring中得到 Http 对象的几种方法
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//
//        //查询Cookie
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
//        if (cookie == null) {
//            log.warn("[登陆校验] cookie中没有 token");
//            //抛出异常,便于程序做统一异常处理
//           // throw new SellerAuthorizeException();
//        }
//        //如果 Cookie 中有 token 值,就去Redis中查询 openid 值
//        String token = cookie.getValue();
//        String openid = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, token));
//        if (StringUtils.isEmpty(openid)) {
//            log.warn("[登陆失败] Redis查不到openid");
//            //throw new SellerAuthorizeException();
//
//        }


    }


}
