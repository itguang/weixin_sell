package com.itguang.weixinsell.converter;

import com.itguang.weixinsell.dto.OrderDTO;
import com.itguang.weixinsell.entity.OrderMasterEntity;
import org.springframework.beans.BeanUtils;
import sun.plugin.viewer.IExplorerAppletStatusListener;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author itguang
 * @create 2017-11-28 16:35
 **/
public class OrderMaster2OrderDetailConverter {


    public static OrderDTO convert(OrderMasterEntity orderMasterEntity){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMasterEntity,orderDTO);
        return orderDTO;

    }

    public static List<OrderDTO> convert(List<OrderMasterEntity> orderMasterEntityList){
        return orderMasterEntityList.stream()
                .map(orderMaster->convert(orderMaster))
                .collect(toList());
    }

}
