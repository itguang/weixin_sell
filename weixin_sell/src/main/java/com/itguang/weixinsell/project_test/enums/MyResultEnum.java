package com.itguang.weixinsell.project_test.enums;

import lombok.Getter;

/**
 *
 * 应用全局错误码
 *
 */
@Getter
public enum MyResultEnum {

    SUCCESS(0,"成功"),

    FAIL(1,"失败"),
    ;


    private Integer code;
    private  String msg;

    MyResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
