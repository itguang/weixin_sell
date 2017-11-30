package com.itguang.weixinsell.service.impl;

import com.itguang.weixinsell.dto.OrderDTO;
import com.itguang.weixinsell.entity.OrderDetailEntity;
import com.itguang.weixinsell.enums.OrderStatusEnum;
import com.itguang.weixinsell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private  OrderServiceImpl orderService;

    private final String BUYER_OPENID = "1101110";


    @Test
    public void create() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("小观光");
        orderDTO.setBuyerAddress("意尔康");
        orderDTO.setBuyerPhone("17638166573");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetailEntity> orderDetailList = new ArrayList<>();
        OrderDetailEntity o1 = new OrderDetailEntity();
        o1.setProductId("123456");
        o1.setProductQuantity(1);


        OrderDetailEntity o2 = new OrderDetailEntity();
        o2.setProductId("1");
        o2.setProductQuantity(2);

        orderDetailList.add(o1);
//        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);

        log.info("【创建订单】result={}", result);

        Assert.assertNotNull(result);


    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1511847585892228554");
        Assert.assertNotNull(orderDTO);
        log.info("[查询单个订单:]"+orderDTO.toString());

    }

    @Test
    public void findList() throws Exception {
        //查询第一页(0),每页1条数据
        Pageable pageable = new PageRequest(1,1);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, pageable);
        log.info("[查找所有的订单]"+orderDTOPage.getContent());


    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1511847585892228554");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());

    }

    @Test
    public void finish() throws Exception {

        OrderDTO orderDTO = orderService.findOne("1511847585892228554");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());


    }

    @Test
    public void paid() throws Exception {

        OrderDTO orderDTO = orderService.findOne("1511847585892228554");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());



    }

    @Test
    public void findList1() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 5);
        Page<OrderDTO> list = orderService.findList(pageRequest);
        Assert.assertNotEquals(0,list.getSize());


    }

}