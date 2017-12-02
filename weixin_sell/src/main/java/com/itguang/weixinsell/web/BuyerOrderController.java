package com.itguang.weixinsell.web;

import com.itguang.weixinsell.VO.ResultOV;
import com.itguang.weixinsell.converter.OrderForm2OrderDTO;
import com.itguang.weixinsell.dto.CartDTO;
import com.itguang.weixinsell.dto.OrderDTO;
import com.itguang.weixinsell.entity.OrderDetailEntity;
import com.itguang.weixinsell.enums.ResultEnum;
import com.itguang.weixinsell.exception.SellException;
import com.itguang.weixinsell.form.OrderForm;
import com.itguang.weixinsell.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author itguang
 * @create 2017-11-28 14:08
 **/
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderServiceImpl orderService;


    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    @RequestMapping("/mycreate")
    public ResultOV saveOrder(OrderDTO orderDTO) {
        try {
            orderService.create(orderDTO);
        } catch (Exception e) {
            return new ResultOV(1, "保存订单失败!");

        }

        return new ResultOV(ResultEnum.SUCCESS);

    }

    @RequestMapping("/create")
    public ResultOV createOrder(@Valid OrderForm orderForm, BindingResult bindingResult) {

        //表单验证
        if (bindingResult.hasErrors()) {
            log.error("{提交订单表] 参数不正确: orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        OrderDTO result = orderService.create(orderDTO);

        if (CollectionUtils.isEmpty(result.getOrderDetailList())){
                throw new SellException(ResultEnum.CART_IS_NULL);
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());

        return new ResultOV(ResultEnum.SUCCESS,map);


    }




    //订单列表
    @RequestMapping("list")
//    @Cacheable(cacheNames = "order",key = "list")
    public ResultOV<List<OrderDTO>> orderList(@RequestParam("openid") String openid,
                                                       @RequestParam(value = "page",defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size",defaultValue = "10") Integer pageSize){
        if (StringUtils.isEmpty(openid)){
            throw new SellException(ResultEnum.OPENID_IS_NULL);

        }
        PageRequest pageRequest = new PageRequest(page - 1, pageSize);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
        List<OrderDTO> orderDTOS = orderDTOPage.getContent();

        ArrayList<OrderDTO> list = new ArrayList<>();
//        list.add(new OrderDTO());

        return new ResultOV(ResultEnum.SUCCESS,list);


    }


    //订单详情
    @RequestMapping("/detail")
    public ResultOV<OrderDetailEntity> detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId){

        // TODO 权限验证

        OrderDTO orderDTO = orderService.findOne(orderId);
        List<OrderDetailEntity> orderDetailList = orderDTO.getOrderDetailList();

        return new ResultOV(ResultEnum.SUCCESS,orderDetailList.get(0));

    }


    //取消订单
    @RequestMapping("cancle")
    public ResultOV cancle(String openid,String orderId){

        // TODO 权限验证

        OrderDTO orderDTO = orderService.findOne(orderId);

        orderService.cancel(orderDTO);

        return new ResultOV(ResultEnum.SUCCESS);

    }



}
