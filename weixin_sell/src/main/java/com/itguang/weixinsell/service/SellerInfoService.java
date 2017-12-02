package com.itguang.weixinsell.service;

import com.itguang.weixinsell.entity.SellerInfoEntity;

public interface SellerInfoService {

    /**
     * 根据openid查找
     * @param openid
     * @return
     */
    SellerInfoEntity findByOpenid(String openid);

}
