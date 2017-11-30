package com.itguang.weixinsell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itguang.weixinsell.entity.OrderDetailEntity;
import com.itguang.weixinsell.enums.OrderStatusEnum;
import com.itguang.weixinsell.enums.PayStatusEnum;
import com.itguang.weixinsell.utils.EnumUtil;
import com.itguang.weixinsell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * @author itguang
 * @create 2017-11-28 8:37
 **/
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
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

    //便于前台 FreeMarker 模版进行赋值操作

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        //遍历 OrderStatus 得到 msg.(抽取 EnmuUtil)
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);

    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        // 遍历 PayStatus 得到 msg. (抽取 EnmuUtil)
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);

    }


}
