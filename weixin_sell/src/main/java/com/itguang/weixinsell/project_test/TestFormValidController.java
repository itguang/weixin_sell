package com.itguang.weixinsell.project_test;

import com.itguang.weixinsell.project_test.pojo.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author itguang
 * @create 2017-12-07 14:42
 **/

@RestController
@RequestMapping("/test")
public class TestFormValidController {

    @RequestMapping("/saveUser")
    public void saveUser(@Valid User user, BindingResult result) {

        System.out.println("user:"+user);

        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode() + "-" + error.getDefaultMessage());
            }
        }

    }


}
