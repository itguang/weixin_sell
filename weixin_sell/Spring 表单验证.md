# Spring 表单验证


从Spring3.0开始,在Spring API 中提供了对java校验API的支持.

在Spring MVC 中要使用java校验API的话,并不需要而外的配置,只要保证在类路径下包含这个java API的实现即可.比如: Hibernate Validator.

参考资料: 

http://www.imooc.com/learn/810

http://gitbook.cn/gitchat/column/59f5daa149cd4330613605ba/topic/59f6922549cd4330613634a4

## 参数校验


参数校验在我们日常开发中非常常见，最基本的校验有判断属性是否为空、长度是否符合要求等，在传统的开发模式中需要写一堆的 if else 来处理这些逻辑，很繁琐，效率也低

使用 @Valid + BindingResult 就可以优雅的解决这些问题

实体类 User

```java
public class User {


    @NotEmpty(message = "用户名不能为空")
    private String name;



    @Digits(integer = 2,fraction = 0,message = "年龄只能两位数")
    @Min(value = 16,message = "年龄不能小于16岁")
    private int age;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6,max = 12,message = "密码长度必须在6-12位之间")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "邮箱格式不正确")
    private String email;

}
```


测试:

只需要对需要校验的对象添加 @Valid 注解即可,这会告知Spring要确保这个对象满足校验限制.

```java

@RestController
@RequestMapping("/test")
public class TestFormValidController {

    @RequestMapping("/saveUser")
    public void saveUser(@Valid User user, BindingResult result) {

        System.out.println("user:"+user);

        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "-" + error.getDefaultMessage());
            }
        }
        
    }


}

```

## java校验api所提供的校验注解

* 1. @AssertFalse 所注解的元素必须为 boolean类型,并且值为 false

* 2. @AssertTrue 所注解的元素必须为 boolean类型,并且值为 true

* 3. Digits 所注解的元素必须是数字,并且它的值必须有指定的位数

* 4. Future 所注解的值必须是一个将来的日期

* 5. Past 所注解的值必须是一个已经过去的日期

* 6. Min 所注解的元素必须是数字,并且它的值必须小于等度给定的值

* 7. Max 所注解的元素必须是数字,并且它的值必须大于等度给定的值

* 8. NotNull 所注解的元素不能为null

* 9. NotEmpty 所注解元素不能为 空(注意不是null)

* 10. Null 做注解的元素必须为null

* 11. Size 所注解的元素必须是String,集合,或数组,并且它的长度要符合给定的范围

* 12. Pattern 所注解的元素的值必须匹配给定的正则表达式




 

























