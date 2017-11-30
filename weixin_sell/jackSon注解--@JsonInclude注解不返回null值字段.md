# jackSon注解-- @JsonInclude 注解不返回null值字段


#  @JsonInclude使用教程???

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

相应还有 @JsonInclude(JsonInclude.Include.NON_EMPTY)


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

















