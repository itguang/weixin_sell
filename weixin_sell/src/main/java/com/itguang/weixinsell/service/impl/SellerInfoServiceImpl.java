package com.itguang.weixinsell.service.impl;

import com.itguang.weixinsell.entity.SellerInfoEntity;
import com.itguang.weixinsell.repository.SellerInfoRepository;
import com.itguang.weixinsell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author itguang
 * @create 2017-12-01 9:46
 **/
@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfoEntity findByOpenid(String openid) {
        SellerInfoEntity sellerInfoEntity = sellerInfoRepository.findByOpenid(openid);
        return sellerInfoEntity;
    }
}
