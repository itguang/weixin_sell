package com.itguang.weixinsell.entity;

import com.itguang.weixinsell.enums.OrderStatusEnum;
import com.itguang.weixinsell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author itguang
 * @create 2017-11-25 14:02
 **/
@Entity
@Data
@DynamicUpdate
@Table(name = "order_master")
public class OrderMasterEntity {

    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;

    /**
     * 订单状态,默认是0
     */
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    private Timestamp createTime;
    private Timestamp updateTime;



}
