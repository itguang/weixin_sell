package com.itguang.weixinsell.service.impl;

import com.itguang.weixinsell.converter.OrderMaster2OrderDetailConverter;
import com.itguang.weixinsell.dto.CartDTO;
import com.itguang.weixinsell.dto.OrderDTO;
import com.itguang.weixinsell.entity.OrderDetailEntity;
import com.itguang.weixinsell.entity.OrderMasterEntity;
import com.itguang.weixinsell.entity.ProductInfoEntity;
import com.itguang.weixinsell.enums.OrderStatusEnum;
import com.itguang.weixinsell.enums.PayStatusEnum;
import com.itguang.weixinsell.enums.ResultEnum;
import com.itguang.weixinsell.exception.SellException;
import com.itguang.weixinsell.repository.OrderDetailRepository;
import com.itguang.weixinsell.repository.OrderMasterRepository;
import com.itguang.weixinsell.service.OrderService;
import com.itguang.weixinsell.service.ProductInfoService;
import com.itguang.weixinsell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author itguang
 * @create 2017-11-28 8:48
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //*********注意***************
        //必须先创建 OrderMaster表再创建orderDetail表,因为外键约束
        //******************************


        //1.计算总价()
        for (OrderDetailEntity orderDetailEntity : orderDTO.getOrderDetailList()) {
            ProductInfoEntity infoEntity = productInfoService.findOne(orderDetailEntity.getProductId());
            if (infoEntity == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            orderAmount = infoEntity.getProductPrice()
                    .multiply(new BigDecimal(orderDetailEntity.getProductQuantity()))
                    .add(orderAmount);

        }
        //2.订单表入库(确保订单表字段设置完毕)
        OrderMasterEntity orderMasterEntity = new OrderMasterEntity();
        BeanUtils.copyProperties(orderDTO, orderMasterEntity);
        orderMasterEntity.setOrderId(orderId);
        orderMasterEntity.setOrderAmount(orderAmount);
        orderMasterEntity.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterEntity.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMasterEntity save = orderMasterRepository.save(orderMasterEntity);

        //3.订单明细表入库
        for (OrderDetailEntity orderDetailEntity : orderDTO.getOrderDetailList()) {
            ProductInfoEntity infoEntity = productInfoService.findOne(orderDetailEntity.getProductId());
            if (infoEntity == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //订单详情入库(确保把订单详情表的字段设置完毕)
            BeanUtils.copyProperties(infoEntity, orderDetailEntity);
            orderDetailEntity.setDetailId(KeyUtil.genUniqueKey());
            orderDetailEntity.setOrderId(orderId);
            orderDetailRepository.save(orderDetailEntity);


        }


        //4.减库存(使用 Lambda 表达式构造 List<CartDTO>)
        List<CartDTO> list = orderDTO.getOrderDetailList().stream()
                .map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                .collect(toList());
        productInfoService.decreaseStock(list);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMasterEntity orderMasterEntity = orderMasterRepository.findOne(orderId);
        if (orderMasterEntity == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetailEntity> detailEntityList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(detailEntityList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMasterEntity, orderDTO);
        orderDTO.setOrderDetailList(detailEntityList);


        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMasterEntity> orderMasterEntityPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDetailConverter.convert(orderMasterEntityPage.getContent());
        //构造一个 Page<OrderDTO> 对象,思路:点进去Page,发现是一个接口,那就按 ctrl+H 看其实现类,找一个实例化
        PageImpl<OrderDTO> orderDTOS = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterEntityPage.getTotalElements());
        return orderDTOS;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMasterEntity> page = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDetailConverter.convert(page.getContent());

        PageImpl<OrderDTO> orderDTOPage = new PageImpl(orderDTOList,pageable,page.getTotalElements());

        return orderDTOPage;
    }


    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDTO cancel(OrderDTO orderDTO) {

        //1.根据orderDTO查找订单id
        OrderMasterEntity orderMasterEntity = orderMasterRepository.findOne(orderDTO.getOrderId());

        //2.判断并设置订单状态
        if(orderMasterEntity.getOrderStatus().equals(OrderStatusEnum.FINISHED.getCode())){
            log.info("[取消订单失败]:orderMaster={}",orderMasterEntity);
            throw new SellException(ResultEnum.ORDER_FINISHED);
        }
        orderMasterEntity.setOrderStatus(OrderStatusEnum.CANCEL.getCode());

        //3.加库存
        List<CartDTO> cartDTOList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            cartDTOList = orderDTO.getOrderDetailList().stream()
                    .map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                    .collect(toList());
        }
        productInfoService.increaseStock(cartDTOList);

        //4.如果已经支付,需要退款
        if (orderMasterEntity.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }

        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        return orderDTO;
    }

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        String orderId = orderDTO.getOrderId();
        OrderMasterEntity masterEntity = orderMasterRepository.findOne(orderId);
        //判断订单是否存在
        if(masterEntity==null){

            log.error("[订单不存在] :orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);

        }
        //判断订单状态
        if (!masterEntity.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){

            log.error("[完结订单失败,订单状态不正确:] orderStatus={}",masterEntity.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //设置订单状态并保存
        masterEntity.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(masterEntity);


        orderDTO.setOrderStatus(masterEntity.getOrderStatus());

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {

        String orderId = orderDTO.getOrderId();
        OrderMasterEntity masterEntity = orderMasterRepository.findOne(orderId);
        //判断订单是否存在
        if(masterEntity==null){

            log.error("[订单不存在] :orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);

        }

        //判断订单状态
        if (!masterEntity.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){

            log.error("[完结订单失败,订单状态不正确:] orderStatus={}",masterEntity.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if(!masterEntity.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[支付状态不正确] payStatus={}",masterEntity.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //设置支付状态并保存
        masterEntity.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderMasterRepository.save(masterEntity);

        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());


        return orderDTO;
    }


}
