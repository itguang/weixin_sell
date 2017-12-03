# Class 类的方法和作用

看到项目中下面这个别人写的类,发现对Class不熟,特地查资料总结一下:

```java
/**
 * 遍历枚举,得到code对应的msg
 *
 * @author itguang
 * @create 2017-11-30 14:58
 **/
public class EnumUtil {


    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {

        //enumClass.getEnumConstants()

        for (T each : enumClass.getEnumConstants()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;

    }
}
```

参考: http://blog.csdn.net/chongxue91/article/details/60961086

# Class 这个类

提起Class,很容易和java中的关键字 class 混淆.二者没什么关系，class不过是描述类的一个关键字。而Class却是保存着运行时信息的类。

在程序运行期间,java运行时系统统始终为所有的对象维护一个被称为运行时的类型标识.

JVM 利用运行时信息选择相应的方法执行.

**而保存这些信息的类称为 Class**

它能做什么？Class类可以帮助我们在程序运行时分析类，说白了就是获取类中的值。可能瞬间就想到了反射，没错！Class一般就是和反射配套使用的，
因为我们向Class提供一个类或一个类的类名，Class就可以提供我们很多信息，
比如属性/方法/修饰符/构造器/类名等等。然后我们就可以进一步进行反射。



























