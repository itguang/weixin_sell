package com.itguang.weixinsell.web;

import com.itguang.weixinsell.dto.OrderDTO;
import com.itguang.weixinsell.enums.ResultEnum;
import com.itguang.weixinsell.exception.SellException;
import com.itguang.weixinsell.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家订单订单
 *
 * @author itguang
 * @create 2017-11-30 14:05
 **/

@Controller
@RequestMapping("seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        log.info("page={},size={}", page, size);

        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        orderDTOPage.getTotalElements();
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("order/list", map);
    }


    @RequestMapping("/cancel")
    public ModelAndView cancle(@RequestParam("orderId") String orderId, Map<String, Object> map) {
        log.info("cancle==============");

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("[卖家端取消订单异常]{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order.list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);

    }

    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId, Map<String, Object> map){

        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
           log.error("[卖家端查看订单详情异常:]{} ",e);
           map.put("msg",e.getMessage());
           map.put("url","/sell/seller/order/list");
           return new ModelAndView("common/error",map);
        }

        map.put("orderDTO",orderDTO);

        return new ModelAndView("order/detail",map);

    }

    @RequestMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,Map<String,Object> map){

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("[卖家端完结订单异常]{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
        }

        map.put("msg",ResultEnum.ORORDER_STATUS_FINISHED.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);

    }




    @RequestMapping("test")
    public ModelAndView test(@RequestParam("orderId") String orderId,Map<String,Object> map){

        log.info("test====={}=========",orderId);
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);


    }





}
