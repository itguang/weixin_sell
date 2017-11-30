package com.itguang.weixinsell.exception;

import com.itguang.weixinsell.enums.ResultEnum;
import lombok.Data;

/**
 * @author itguang
 * @create 2017-11-28 8:58
 **/
@Data
public class SellException extends RuntimeException {


    private Integer code;
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
