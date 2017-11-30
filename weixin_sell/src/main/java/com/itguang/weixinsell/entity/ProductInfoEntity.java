package com.itguang.weixinsell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicInsert
@Data
@Table(name = "product_info")
public class ProductInfoEntity {

    @Id
    @Column(name = "product_id")
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private int productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus;
    private int categoryType;
    private Timestamp createTime;
    private Timestamp updateTime;


}
