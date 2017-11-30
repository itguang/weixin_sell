package com.itguang.weixinsell.enums;

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
