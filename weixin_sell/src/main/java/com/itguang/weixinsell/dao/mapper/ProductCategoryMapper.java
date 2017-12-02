package com.itguang.weixinsell.dao.mapper;

import com.itguang.weixinsell.entity.ProductCategoryEntity;
import org.apache.ibatis.annotations.Insert;

import java.util.Map;

/**
 * @author itguang
 * @create 2017-12-02 8:39
 **/
public interface ProductCategoryMapper {



    @Insert("insert into product_category (category_name,category_type) values(#{categoryName},#{categoryType})")
    int inseret2(Map<String,Object> map);


    @Insert("insert into product_category (category_name,category_type) values(#{categoryName},#{categoryType})")
    int inseret(ProductCategoryEntity categoryEntity);


   ProductCategoryEntity selectByCategoryType(int categoryType);











}
