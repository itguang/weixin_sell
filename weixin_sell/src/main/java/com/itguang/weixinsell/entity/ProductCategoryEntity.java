package com.itguang.weixinsell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author itguang
 * @create 2017-11-25 14:02
 **/
@Entity
@DynamicUpdate
@Data
@Table(name = "product_category")
public class ProductCategoryEntity {

    @Id
    private int categoryId;
    private String categoryName;
    private int categoryType;
    private Timestamp createTime;
    private Timestamp updateTime;


}
