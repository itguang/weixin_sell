package com.itguang.weixinsell.project_test.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author itguang
 * @create 2017-12-07 14:37
 **/
@Data
public class User {


    @NotEmpty(message = "用户名不能为空")
    private String name;

    @Digits(integer = 2,fraction = 0,message = "年龄只能两位数")
    @Min(value = 16,message = "年龄不能小于16岁")
    private int age;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6,max = 12,message = "密码长度必须在6-12位之间")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "邮箱格式不正确")
    private String email;

}
