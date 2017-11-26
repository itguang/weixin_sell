package com.itguang.weixinsell.service;

import com.itguang.weixinsell.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author itguang
 * @create 2017-11-26 13:24
 **/

@Service
@Transactional
public class ProductInfoService {

    @Autowired
    private ProductInfoRepository infoRepository;


    public Integer updatePrice( String id,double price){
        Integer i = infoRepository.updatePrice(id, price);
        return i;

    }


}
