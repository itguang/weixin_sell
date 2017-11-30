package com.itguang.weixinsell.service.impl;

import com.itguang.weixinsell.dto.CartDTO;
import com.itguang.weixinsell.entity.ProductInfoEntity;
import com.itguang.weixinsell.enums.ResultEnum;
import com.itguang.weixinsell.exception.SellException;
import com.itguang.weixinsell.repository.ProductInfoRepository;
import com.itguang.weixinsell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author itguang
 * @create 2017-11-27 10:22
 **/
@SuppressWarnings("ALL")
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository infoRepository;

    @Override
    public ProductInfoEntity findOne(String id) {
        return infoRepository.findOne(id);
    }

    /**
     * 查询所有上架商品
     *
     * @param status 0正常/1下架
     * @return
     */
    @Override
    public List<ProductInfoEntity> findByProductStatus(Integer status) {

        List<ProductInfoEntity> infoEntityList = infoRepository.findByProductStatus(status);

        return infoRepository.findByProductStatus(status);
    }

    @Override
    public Page<ProductInfoEntity> findAll(Pageable pageable) {


        return infoRepository.findAll(pageable);
    }

    @Override
    public ProductInfoEntity save(ProductInfoEntity infoEntity) {
        return infoRepository.save(infoEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseStock(List<CartDTO> cartDTOS) {

        for (CartDTO cartDTO : cartDTOS) {
            ProductInfoEntity productInfoEntity = infoRepository.findOne(cartDTO.getProductId());
            if(productInfoEntity==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfoEntity.getProductStock() + cartDTO.getProductQuantity();
            productInfoEntity.setProductStock(result);

            infoRepository.save(productInfoEntity);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(List<CartDTO> cartDTOS) {
        for (CartDTO cartDTO : cartDTOS) {
            ProductInfoEntity one = infoRepository.findOne(cartDTO.getProductId());
            if (one == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int i = one.getProductStock() - cartDTO.getProductQuantity();
            if (i < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            one.setProductStock(i);
            infoRepository.save(one);

        }

    }
}
