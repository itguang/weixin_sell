package com.itguang.weixinsell.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.itguang.weixinsell.dto.OrderDTO;
import com.itguang.weixinsell.entity.OrderDetailEntity;
import com.itguang.weixinsell.enums.ResultEnum;
import com.itguang.weixinsell.exception.SellException;
import com.itguang.weixinsell.form.OrderForm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author itguang
 * @create 2017-11-29 16:32
 **/
@Data
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm) {

        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        //json类型的购物车转成OrderDetailList

        ArrayList<OrderDetailEntity>  orderDetailEntities = null;
        try {
            orderDetailEntities = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetailEntity>>() {
            }.getType());
        } catch (JsonSyntaxException e) {

            log.error("[对象转换错误] string={} ",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailEntities);

        return orderDTO;

    }


}
