

> 参考慕课网地址: Aop之统一异常处理 http://www.imooc.com/learn/810

# SpringBoot 统一异常处理--- @ControllerAdvice 使用

**使用Spring MVC的@ControllerAdvice注解做Json的异常处理**

在实际项目中经常会抛出各种各样的异常,有时候需要对异常信息进行统一处理,以友好正确的形式返回给调用方.


使用Spring MVC的@ControllerAdvice 注解就可以捕获全局异常.

比如我们有一个Controller,接收一个String类型的 username 作为参数,当username=root时,返回成功信息,否则返回错误信息

```java
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
```
注意: MyException 就是我们的自定义异常,在程序出现错误时都可以 手动抛出此异常,并使用一个枚举类 MyResultEnum,来封装错误信息,当程序抛出此异常后,就会进入异常处理
然后我们就可以全局捕获 这个异常信息,并把异常所携带信息以适当是形式展现给调用方.

**自定义全局异常处理类 GlobalExceptionHandler.java**

```java
/**
 * @author itguang
 * @create 2017-12-02 15:34
 **/

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MyException.class)
   
    @ResponseBody
    public Result handleMyException(HttpServletRequest request, MyException e){
        String message = e.getMessage();
        Integer code = e.getCode();

        Result result = new Result(code,message);

        return result;


    }
}
```
只需要使用 @ControllerAdvice 注解来标识即可,可以结合 @Controller 注解来理解.

**@Controller 注解标识的类 拦截所有的web请求,使用 @RequestMappering() 进行匹配**

**@ControllerAdvice 注解标识的类 拦截程序抛出的异常,使用@ExceptionHandler() 进行匹配**


顺便把枚举类 MyResultEnum 的代码也贴出来

```java
@Getter
public enum MyResultEnum {

    SUCCESS(0,"成功"),

    FAIL(1,"失败"),
    ;


    private Integer code;
    private  String msg;

    MyResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
```

## 测试

浏览器访问 localhost/sell/test1/root
返回:
```json
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```
浏览器访问 localhost/sell/test1/java
返回:

```json
{
    "code": 1,
    "msg": "失败",
    "data": null
}
```


















































