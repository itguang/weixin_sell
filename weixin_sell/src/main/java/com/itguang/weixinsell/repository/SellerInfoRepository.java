package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.SellerInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfoEntity,String> {

    /**
     * 根据openid查找
     * @param openid
     * @return
     */
    SellerInfoEntity findByOpenid(String openid);


}
