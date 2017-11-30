package com.itguang.weixinsell.VO;

import com.itguang.weixinsell.enums.ResultEnum;
import lombok.Data;

/**
 * http请求返回的最外层对象
 *
 * @author itguang
 * @create 2017-11-27 13:19
 **/
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultOV<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;

    public ResultOV() {
    }

    public ResultOV(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultOV(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultOV(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public ResultOV(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }

    public static ResultOV success() {

        return new ResultOV(ResultEnum.SUCCESS);

    }

    public static ResultOV fail(ResultEnum resultEnum, Object data) {

        return new ResultOV(resultEnum, data);

    }


}
