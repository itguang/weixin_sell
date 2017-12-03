package com.itguang.weixinsell.project_test.vo;

import com.itguang.weixinsell.project_test.enums.MyResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回的最外层对象
 *
 * @author itguang
 * @create 2017-11-27 13:19
 **/
@Data
public class Result<T> implements Serializable{

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

    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(MyResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public Result(MyResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }

    public static Result success() {

        return new Result(MyResultEnum.SUCCESS);

    }

    public static Result fail(MyResultEnum resultEnum, Object data) {

        return new Result(resultEnum, data);

    }


}
