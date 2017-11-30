package com.itguang.weixinsell.utils;

import com.itguang.weixinsell.enums.CodeEnum;

/**
 * 遍历枚举,得到code对应的msg
 *
 * @author itguang
 * @create 2017-11-30 14:58
 **/
public class EnumUtil {


    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {

        //enumClass.getEnumConstants()

        for (T each : enumClass.getEnumConstants()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;

    }
}
