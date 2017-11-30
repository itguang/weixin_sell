package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,String> {

    /**
     *
     * @param orderId
     * @return
     */
    List<OrderDetailEntity> findByOrderId(String orderId);

}
