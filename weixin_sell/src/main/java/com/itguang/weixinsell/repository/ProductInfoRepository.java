package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.ProductInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity, String> ,JpaSpecificationExecutor<ProductInfoEntity>{

    List<ProductInfoEntity> findAllByProductName(String name);


    /**
     * 查询所有上架商品
     * @param status 0正常/1下架
     * @return
     */
    List<ProductInfoEntity> findByProductStatus(Integer status);


    /**
     * 使用约定命名规则
     * @param name
     * @param price
     * @return
     */
    List<ProductInfoEntity> findByProductNameStartingWithAndProductPriceLessThan(String name, Double price);

    List<ProductInfoEntity> findByProductNameInAndProductPriceLessThan(List<String> names, Double price);

    /**
     *使用JPA SQL语句
     * @return
     */
    @Query("select p from ProductInfoEntity p where p.productName like '%米%' ")
    List<ProductInfoEntity> findProductInfo();

    /**
     * 使用JPA SQL语句 查询价格最高的商品
     */
    @Query("select p from ProductInfoEntity p " +
            "where p.productPrice=" +
            "(select max(p2.productPrice) from ProductInfoEntity  p2)")
    List<ProductInfoEntity> findMaxPrice();

    /**
     * 使用JPA SQL语句 带参数的查询1
     *
     * @param name
     * @param price
     * @return
     */
    @Query("select o from ProductInfoEntity  o where o.productName=?1 and o.productPrice=?2")
    List<ProductInfoEntity> findParam(String name, double price);


    /**
     * 使用JPASQL语句 带参数的查询2
     *
     * @param name
     * @param price
     * @return
     */
    @Query("select o from ProductInfoEntity  o where o.productName=:name and o.productPrice=:price")
    List<ProductInfoEntity> findParam2(@Param("name") String name, @Param("price") double price);

    /**
     * 使用原生SQL语句 查询
     * @return
     */
    @Query(nativeQuery = true,value = "select count(*) from product_info")
    Integer getCount();


    @Modifying
    @Query("update ProductInfoEntity  o set o.productPrice =:price where o.productId=:id")
    Integer updatePrice(@Param("id") String id,@Param("price") double price);




}
