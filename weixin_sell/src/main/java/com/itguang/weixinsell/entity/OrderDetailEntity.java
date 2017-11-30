package com.itguang.weixinsell.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
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
@DynamicUpdate

@Table(name = "order_detail", schema = "sell")
@Data
public class OrderDetailEntity {

    @Id
    private String detailId;
    private String orderId;
    @JsonProperty("productId")
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private String productIcon;
    @JsonProperty("productQuantity")
    private int productQuantity;
    private Timestamp createTime;
    private Timestamp updateTime;


}
