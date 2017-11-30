# 枚举 Enmu使用

定义一个枚举类 ProductStatusEnum

```java
import lombok.Getter;

/**
 *商品上下架状态
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"上架"),
    DOWN(1,"下架")
    ;

    private Integer Code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        Code = code;
        this.message = message;
    }
}
```

使用:

```java
 @Test
    public void enmuTest(){
        Integer upCode = ProductStatusEnum.UP.getCode();
        Integer downCode = ProductStatusEnum.DOWN.getCode();

        System.out.println(upCode);//0
        System.out.println(downCode);//1


    }
```



