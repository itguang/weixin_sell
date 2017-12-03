# jackSon注解-- @JsonInclude 注解不返回null值字段


> **Spring Boot项目中遇到的小知识**

```java
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;
    @JsonProperty("name")
    private String buyerName;
    @JsonProperty("phone")
    private String buyerPhone;
    @JsonProperty("address")
    private String buyerAddress;
    @JsonProperty("openid")
    private String buyerOpenid;
    private BigDecimal orderAmount;

    /**
     * 订单状态,默认是0
     */
    private Integer orderStatus;

    /**
     * 支付状态
     */
    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp updateTime;

    @JsonProperty("items")
    List<OrderDetailEntity> orderDetailList;


}
```

@JsonInclude(JsonInclude.Include.NON_NULL)表示,如果值为null,则不返回



## 全局jsckson配置

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: 123456
    url: jdbc:mysql://192.168.41.60/sell?characterEncoding=utf-8&useSSL=false
  jpa:
    show-sql: true
  jackson:
    default-property-inclusion: non_null # 全局jackson配置
```


# JSON库 Jackson 常用注解介绍

> 注:以下所涉及到的实体类都使用了 Lomback 插件

Jackson JSON 框架中包含了大量的注解来让我们可以干预 Jackson 的 JSON 处理过程，
例如我们可以通过注解指定 java pojo 的某些属性在生成 json 时被忽略。。本文主要介绍如何使用 Jackson 提供的注解。
Jackson注解主要分成三类，一是只在序列化时生效的注解；二是只在反序列化时候生效的注解；三是两种情况下都生效的注解。

## 一: 两种情况下都有效的注解

### 1. @JsonIgnore 作用域属性或方法上

@JsonIgnore 用来告诉 Jackson 在处理时忽略该注解标注的 java pojo 属性，
不管是将 java 对象转换成 json 字符串，还是将 json 字符串转换成 java 对象。

```java
@Data
public class SellerInfoEntity {

    private String id;
    private String username;
    private String password;
    private String openid;

    @JsonIgnore
    private Timestamp createTime;
    @JsonIgnore
    private Timestamp updateTime;


    public SellerInfoEntity() {
    }

    public SellerInfoEntity(String id, String username, String password, String openid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.openid = openid;
    }
}
```


### 2. @JsonIgnoreProperties 作用在类上

@JsonIgnoreProperties 和 @JsonIgnore 的作用相同，都是告诉 Jackson 该忽略哪些属性，
不同之处是 @JsonIgnoreProperties 是类级别的，并且可以同时指定多个属性。

```java
@Data
@JsonIgnoreProperties(value = {"createTime","updateTime"})
public class SellerInfoEntity {

    private String id;
    private String username;
    private String password;
    private String openid;

    private Timestamp createTime;
    private Timestamp updateTime;


    public SellerInfoEntity() {
    }

    public SellerInfoEntity(String id, String username, String password, String openid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.openid = openid;
    }
}
```

使用Spring Boot快速搭建Controller进行测试:

```java
@RestController
@RequestMapping("/jackson")
public class TestJackson {



    @RequestMapping("test1")
    public Result test1(){

        SellerInfoEntity entity = new SellerInfoEntity("1","user1","123456","openid");

        return new Result(MyResultEnum.SUCCESS,entity);

    }





}
```

访问: localhost/sell/jackson/test1

使用注解前:返回值

```json
{
    "code": 0,
    "msg": "成功",
    "data": {
        "id": "1",
        "username": "user1",
        "password": "123456",
        "openid": "openid",
        "createTime": null,
        "updateTime": null
    }
}
```

使用注解后:返回值

```json
{
    "code": 0,
    "msg": "成功",
    "data": {
        "id": "1",
        "username": "user1",
        "password": "123456",
        "openid": "openid",
    }
}
```

### 3.  @JsonIgnoreType 

@JsonIgnoreType 标注在类上，当其他类有该类作为属性时，该属性将被忽略。

```java
package org.lifw.jackosn.annotation;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
@JsonIgnoreType
public class SomeOtherEntity {
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
```

```java
public class SomeEntity {
    private String name;
    private String desc;
    private SomeOtherEntity entity;
}
```

SomeEntity 中的 entity 属性在json处理时会被忽略。

### 4.  @JsonProperty

@JsonProperty 可以指定某个属性和json映射的名称。例如我们有个json字符串为{"user_name":"aaa"}，
而java中命名要遵循驼峰规则，则为userName，这时通过@JsonProperty 注解来指定两者的映射规则即可。这个注解也比较常用。

```java
public class SomeEntity {
    @JsonProperty("user_name")
    private String userName;
      // ...
}
```


## 二、只在序列化情况下生效的注解

### 1. @JsonPropertyOrder

在将 java pojo 对象序列化成为 json 字符串时，使用 @JsonPropertyOrder 可以指定属性在 json 字符串中的顺序。


### 2.  @JsonInclude

在将 java pojo 对象序列化成为 json 字符串时，使用 @JsonInclude 注解可以控制在哪些情况下才将被注解的属性转换成 json，例如只有属性不为 null 时。

```java
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerInfoEntity {

    private String id;
    private String username;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;
    private String openid;

    private Timestamp createTime;
    private Timestamp updateTime;


    public SellerInfoEntity() {
    }

    public SellerInfoEntity(String id, String username, String password, String openid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.openid = openid;
    }
}

```

Controller 测试

```java
@RestController
@RequestMapping("/jackson")
public class TestJackson {

    @RequestMapping("test1")
    public Result test1(){

        SellerInfoEntity entity = new SellerInfoEntity("1","user1","","openid");

        return new Result(MyResultEnum.SUCCESS,entity);

    }

}
```

结果:

```json
{
    "code": 0,
    "msg": "成功",
    "data": {
        "id": "1",
        "username": "user1",
        "openid": "openid"
    }
}

```

上述例子的意思是 SellerInfoEntity 的所有属性只有在不为 null 的时候才被转换成 json，
如果为 null 就被忽略。并且如果password为空字符串也不会被转换.

该注解也可以加在某个字段上。

另外还有很多其它的范围，例如 NON_EMPTY、NON_DEFAULT等

## 三、是在反序列化情况下生效的注解

### 1. @JsonSetter

@JsonSetter 标注于 setter 方法上，类似 @JsonProperty ，也可以解决 json 键名称和 java pojo 字段名称不匹配的问题。

```java
public class SomeEntity {
    private String desc;
    @JsonSetter("description")
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
```

上述例子中在将 json 字符串转换成 SomeEntity 实例时，会将 json 字符串中的 description 字段赋值给 SomeEntity 的 desc 属性。














