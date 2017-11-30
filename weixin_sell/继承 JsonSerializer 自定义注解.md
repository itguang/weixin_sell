# JsonSerializer 自定义注解 @JsonSerialize.

自定义 JsonSerializer

```java

/**
 * 扩展 JsonSerializer 实现自己的 注解
 * @author itguang
 * @create 2017-11-29 17:42
 **/
public class Date2LongSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(date.getTime()/1_000);
    }
}
```


使用:

```java
 @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Timestamp updateTime;
```






































