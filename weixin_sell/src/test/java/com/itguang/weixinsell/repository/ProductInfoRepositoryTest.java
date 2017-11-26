package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.ProductInfoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository infoRepository;

    @Test
    public void test1() {
        List<ProductInfoEntity> productInfoEntities = infoRepository.findAllByProductName("北京烤鸭");
        System.out.println(productInfoEntities.toString());

    }


    @Test
    public void test2() {
        List<ProductInfoEntity> productInfoEntities = infoRepository.findProductInfo();
        System.out.println(productInfoEntities.toString());

    }

    @Test
    public void test3() {
        List<ProductInfoEntity> productInfoEntities = infoRepository.findByProductNameStartingWithAndProductPriceLessThan("黄焖鸡", 10.0);
        productInfoEntities.stream()
                .forEach(System.out::println);

    }

    @Test
    public void findMaxPriceTest() {
        List<ProductInfoEntity> productInfoEntities = infoRepository.findMaxPrice();
        productInfoEntities.stream()
                .forEach(System.out::println);

    }

    @Test
    public void findParamTest() {
        List<ProductInfoEntity> productInfoEntities = infoRepository.findParam("北京烤鸭", 120);
        productInfoEntities.stream()
                .forEach(System.out::println);

    }

    @Test
    public void findParam2Test() {
        List<ProductInfoEntity> productInfoEntities = infoRepository.findParam2("北京烤鸭", 120);
        productInfoEntities.stream()
                .forEach(System.out::println);

    }


    @Test
    public void getCountTest() {
        Integer count = infoRepository.getCount();
        System.out.println(count);

    }

    @Test
    public void updatePriceTest() {
        Integer count = infoRepository.updatePrice("1", 16);
        System.out.println(count);

    }


    //分页查询

    @Test
    public void pageAndSortingTest() {
        Pageable pageable = new PageRequest(0, 5);
        Page<ProductInfoEntity> page = infoRepository.findAll(pageable);

        System.out.println("总页数=" + page.getTotalPages());
        //使用Lambda进行遍历
        List<ProductInfoEntity> list = page.getContent();
        list.stream()
                .forEach(System.out::println);

        System.out.println("本页包含记录=" + page.getContent());
        System.out.println("总记录数=" + page.getTotalElements());
        System.out.println("当前第几页=" + page.getNumber());
        System.out.println("页大小=" + page.getSize());
        System.out.println("是否还有下一页=" + page.hasNext());
        System.out.println("是否还有上一页=" + page.hasPrevious());
        System.out.println("是否是第一页=" + page.isFirst());
        System.out.println("是否是最后一页=" + page.isLast());

    }

    //分页并排序
    @Test
    public void testPageAndSort(){

        //按照价格降序
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"productPrice");
        //按照库存量
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "productStock");


        //需要注意,排序顺序按照添加到 列表中的顺序进行排序
        ArrayList<Sort.Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order);

        Sort sort = new Sort(orders);

        Pageable pageable = new PageRequest(0, 5,sort);

        Page<ProductInfoEntity> page = infoRepository.findAll(pageable);

        System.out.println("总页数=" + page.getTotalPages());
        //使用Lambda进行遍历
        List<ProductInfoEntity> list = page.getContent();
        list.stream()
                .forEach(System.out::println);

        System.out.println("本页包含记录=" + page.getContent());
        System.out.println("总记录数=" + page.getTotalElements());
        System.out.println("当前第几页=" + page.getNumber());
        System.out.println("页大小=" + page.getSize());
        System.out.println("是否还有下一页=" + page.hasNext());
        System.out.println("是否还有上一页=" + page.hasPrevious());
        System.out.println("是否是第一页=" + page.isFirst());
        System.out.println("是否是最后一页=" + page.isLast());


    }


    /**
     *  findAll(Sort sort)
     */
    @Test
    public void testJpa1(){
        //按价格降序
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "productPrice");
        Sort sort = new Sort(order);
        List<ProductInfoEntity> list = infoRepository.findAll(sort);
        list.stream()
                .forEach(System.out::println);


    }

    /**
     *  flush
     */
    @Test
    public void testJpa2(){
        ProductInfoEntity infoEntity = new ProductInfoEntity();
        infoEntity.setProductId("1234561");
        infoEntity.setProductName("大盘鸡");
        infoEntity.setProductPrice(100.0);

        //保存并强制缓存与数据库同步
        ProductInfoEntity entity = infoRepository.save(infoEntity);
        System.out.println(entity);
        infoRepository.flush();
        System.out.println(entity);


    }


    /**
     *  JpaSpecificationExecutor
     */
    @Test
    public void testJpaSpecificationExecutor(){

        //按照价格降序
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"productPrice");
        //按照库存量
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "productStock");


        //需要注意,排序顺序按照添加到 列表中的顺序进行排序
        ArrayList<Sort.Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order);

        Sort sort = new Sort(orders);

        Pageable pageable = new PageRequest(0, 5,sort);

        /**
         * root:就是我们要查询的类型 ProductInfoEntity
         * query: 查询条件
         * cb: 构建Predicate(断言)
         *
         */
        Specification<ProductInfoEntity> specification = new Specification<ProductInfoEntity>(){
            @Override
            public Predicate toPredicate(Root<ProductInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path path = root.get("productPrice");
                //查询条件:价格大于100
                Predicate predicate = cb.gt(path, 100.0);

                return predicate;
            }
        };

        Page<ProductInfoEntity> page = infoRepository.findAll(specification,pageable);
//        Page<ProductInfoEntity> page = infoRepository.findAll(pageable);

        System.out.println("总页数=" + page.getTotalPages());
        //使用Lambda进行遍历
        List<ProductInfoEntity> list = page.getContent();
        list.stream()
                .forEach(System.out::println);

        System.out.println("本页包含记录=" + page.getContent());
        System.out.println("总记录数=" + page.getTotalElements());
        System.out.println("当前第几页=" + page.getNumber());
        System.out.println("页大小=" + page.getSize());
        System.out.println("是否还有下一页=" + page.hasNext());
        System.out.println("是否还有上一页=" + page.hasPrevious());
        System.out.println("是否是第一页=" + page.isFirst());
        System.out.println("是否是最后一页=" + page.isLast());


    }






}