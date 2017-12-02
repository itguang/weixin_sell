package com.itguang.weixinsell.web;

import com.itguang.weixinsell.VO.ProductInfoVO;
import com.itguang.weixinsell.VO.ProductVO;
import com.itguang.weixinsell.VO.ResultOV;
import com.itguang.weixinsell.entity.ProductCategoryEntity;
import com.itguang.weixinsell.entity.ProductInfoEntity;
import com.itguang.weixinsell.enums.ProductStatusEnum;
import com.itguang.weixinsell.service.impl.CategoryServiceImpl;
import com.itguang.weixinsell.service.impl.ProductInfoServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 买家商品
 *
 * @author itguang
 * @create 2017-11-27 13:13
 **/
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoServiceImpl infoService;
    @Autowired
    private CategoryServiceImpl categoryService;

    @RequestMapping("/list")
    @Cacheable(cacheNames = "product",key = "123",unless = "#result.getCode()!=0")
    public ResultOV list() {
        //查询所有上架的商品
        List<ProductInfoEntity> infoEntityList = infoService.findByProductStatus(ProductStatusEnum.UP.getCode());

        //查询所有类目
        List<ProductCategoryEntity> categoryEntityList = categoryService.findAll();

        ArrayList<ProductVO> productVOS = new ArrayList<>();
        for (ProductCategoryEntity categoryEntity : categoryEntityList) {


            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(categoryEntity.getCategoryName());
            productVO.setCategoryType(categoryEntity.getCategoryType());

            ArrayList<ProductInfoVO> infoVOArrayList = new ArrayList<>();
            for (ProductInfoEntity infoEntity : infoEntityList) {
                if (infoEntity.getCategoryType() == categoryEntity.getCategoryType()) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(infoEntity, productInfoVO);
                    infoVOArrayList.add(productInfoVO);

                }
            }


            productVO.setProductInfoVOList(infoVOArrayList);
            productVOS.add(productVO);

        }


        ResultOV resultOV = new ResultOV(0, "成功", productVOS);
        return resultOV;
    }

    @RequestMapping("/cachePut")
//    @CachePut(cacheNames = "product",key = "123")
    @CacheEvict(cacheNames = "product",key = "#id",condition = "#id.length()>3")
    public void test (String id){


    }

}
