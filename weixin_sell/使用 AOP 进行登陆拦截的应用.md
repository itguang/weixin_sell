# 使用 AOP 进行登陆拦截的应用


```java
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Pointcut("execution(public * com.itguang.weixinsell.web.Seller*.*(..))" +
            "&& !execution(public * com.itguang.weixinsell.SellUserController.*(..))")
    public void verify() {
    }

    ;


    @org.aspectj.lang.annotation.Before("verify()")
    public void doVerify() {

        //在Spring中得到 Http 对象的几种方法
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //查询Cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("[登陆校验] cookie中没有 token");
            //抛出异常,便于程序做统一异常处理
            throw new SellerAuthorizeException();
        }
        //如果 Cookie 中有 token 值,就去Redis中查询 openid 值
        String token = cookie.getValue();
        String openid = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, token));
        if (StringUtils.isEmpty(openid)) {
            log.warn("[登陆失败] Redis查不到openid");
            throw new SellerAuthorizeException();

        }


    }


}

```














































