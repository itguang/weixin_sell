package com.itguang.weixinsell.enums;

import lombok.Getter;

/**
 *
 * 应用全局错误码
 *
 */
@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),

    FAIL(1,"失败"),

    PRODUCT_NOT_EXIST(100,"商品不存在"),

    PRODUCT_STOCK_ERROR(101,"库存不足"),

    ORDER_NOT_EXIST(102,"订单不存在"),

    ORDER_DETAIL_NOT_EXIST(103,"订单详情不存在"),

    ORDER_FINISHED(104,"订单状态已完结,不能取消"),

    ORDER_STATUS_ERROR(105,"完结订单失败,订单状态不正确"),

    ORDER_PAY_STATUS_ERROR(106,"付款失败,支付状态不正确"),

    PARAM_ERROR(107,"提交参数有误"),

    CART_IS_NULL(108,"购物车不能为空"),

    OPENID_IS_NULL(109,"微信openid不能为空"),

    WECGAT_ERROR(110,"微信网页授权失败"),

    ORDER_CANCEL_SUCCESS(111,"订单取消成功"),

    ORORDER_STATUS_FINISHED(112,"订单完结成功")

    ;


    private Integer code;
    private  String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
