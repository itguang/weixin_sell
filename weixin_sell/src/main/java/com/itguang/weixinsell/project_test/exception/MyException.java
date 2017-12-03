package com.itguang.weixinsell.project_test.exception;

import com.itguang.weixinsell.enums.ResultEnum;
import com.itguang.weixinsell.project_test.enums.MyResultEnum;
import lombok.Data;

/**
 * @author itguang
 * @create 2017-12-02 15:15
 **/

public class MyException extends RuntimeException {

    /**
     异常码
     */
   private Integer code;

    public MyException(MyResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
