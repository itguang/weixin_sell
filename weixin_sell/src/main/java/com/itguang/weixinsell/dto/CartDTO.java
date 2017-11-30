package com.itguang.weixinsell.dto;

import lombok.Data;

/**
 * @author itguang
 * @create 2017-11-28 10:14
 **/
@Data
public class CartDTO {
    /**
     * 商品id
     */
   private String productId;
    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
