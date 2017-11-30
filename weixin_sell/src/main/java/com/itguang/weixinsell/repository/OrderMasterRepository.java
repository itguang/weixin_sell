package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.OrderMasterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author itguang
 * @create 2017-11-27 17:30
 **/
public interface OrderMasterRepository extends JpaRepository<OrderMasterEntity,String> {

    /**
     * 根据openid 查询所有订单
     * @param buyerOpenid 微信openid
     * @param pageable
     * @return
     */
   Page<OrderMasterEntity>  findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
