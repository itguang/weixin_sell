# Spring Data JPA

先上例子:

```java
package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.ProductInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity, String> {

    List<ProductInfoEntity> findAllByProductName(String name);


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




}


```


## 借助 Spring Data实现自动化的JPA Repostory





编写Spring Data JPA Repository 的关键在于从一组接口中挑选一个进行扩展.

如:

```java
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity,Integer> {
}
```


* 1）Repository是一个空接口，标记接口
没有包含方法声明的接口

* 2）如果我们定义的接口EmployeeRepository extends Repository

如果我们自己的接口没有extends Repository，运行时会报错：

`org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.imooc.repository.EmployeeRepository' available
`
* 3) 添加注解能到达到不用extends Repository的功能

`@RepositoryDefinition(domainClass = Employee.class, idClass = Integer.class)`

这里,ProductCategoryRepository扩展了 JpaRepository 接口,稍后我们会介绍其它几个接口.


通过这种方式,JPARepository 进行了参数化,所以它就能知道这是一个用来持久化 ProductCategoryEntity 的Repository.
并且id类型为 Integer .

另外,它还会集成18个执行持久化操作的通用方法.

**在spring boot 中,如果使用了 spring-boot-starter-data-jpa ,会自动扫描所有扩展了Repository 接口的类,如果是Spring,则需要开启 Repository扫描**

因为 ProductCategoryRepository 扩展了 JpaRepository 接口,而 JpaRepository 接口又间接扩展了 Repository 接口,所以:
当Spring Data 扫描到它时,就会自动创建  ProductCategoryRepository 的实现类,其中包含了 JpaRepository ,PagingAndSortingRepository ,和CrudRepository的18个方法.

**很重要的一点就是,Repository的实现类是在应用启动的时候生成的,也就是Spring的应用上下文创建的时候.而不是通过代码生成技术产生的,也不是接口方法调用时才产生的**

## Repository 的几个实现类

![](images/6.png)







### 首先看下 CrudRepository接口

这个接口提供了通用的CRUD操作

![](images/7.png)

有保存一个或多个, 查询一个或多个,删除一个或多个.

值得一提的是: JPA中的更新操作是通过 先查询一个再保存来更新的.

```java
 @Test
    public void updateTest(){
        ProductCategoryEntity productCategoryEntity = categoryRepository.findOne(1);
        productCategoryEntity.setCategoryName("图书");

        categoryRepository.save(productCategoryEntity);

    }
```
只要 id一样,就会更新,而不是添加.

### PagingAndSortingRepository 分页排序接口

这个接口很简单;

```java
@NoRepositoryBean
public interface PagingAndSortingRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

	/**
	 * Returns all entities sorted by the given options.
	 * 
	 * @param sort
	 * @return all entities sorted by the given options
	 */
	Iterable<T> findAll(Sort sort);

	/**
	 * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
	 * 
	 * @param pageable
	 * @return a page of entities
	 */
	Page<T> findAll(Pageable pageable);
}
```


## 查询方法定义的规则和使用

![](images/8.png)











Spring Data JPA 默认提供了 18个便利的方法进行通用的JPA操作.但是如果你的需求超过了它所提供的这18个方法,该怎么办呢?

幸好,Spring Data JPA提供了几种方法来为Repository 添加自定义的方法.

## 自定义查询方法

 先看一个例子:
 
 ```java

public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity,String> {
    //定义一个方法:根据商品名称查找所有的商品
   List<ProductInfoEntity> findAllByProductName(String name);

}
```
使用:事实上,我们并不需要实现该方法,方法签名已经告诉 Spring Data JPA 足够的信息来创建这个方法的实现了.

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository infoRepository;

    @Test
    public void test1(){
        List<ProductInfoEntity> productInfoEntities = infoRepository.findAllByProductName("北京烤鸭");
        System.out.println(productInfoEntities.toString());

    }

}
```

当创建 Repository 实现的时候,Spring Data会检查 Repository 接口的所有方法,解析方法的名称,并基于被持久化的对象来推测方法的目的.
本质上,Spring Data 定义了一组小型的领域特定语言(DSL),在这里持久化的细节都是通过 Repository的方法签名来描述的.


findAllByProductName(String name) 方法非常简单,但是Spring Data也能处理更加有意思的方法签名.
**Repoditory 方法是 由一个动词,一个可选主题,关键词By以及一个断言所组成.**
在 findAllByProductName 方法中,动词是findAll ,断言是 ProductName,主题并没有指定,暗含就是 ProductInfoEntity.

Spring Data 允许在方法中使用四种动词: get ,read, find , count . 其中动词 get, read,find 是同义的,这三个动词对用的Repository方法都会查询数据并返回对象.
.而动词 count 则会返回匹配对象的数量,而不是对象本身.

Repository 方法的主题是可选的,它主要是让你命名方法的时候有很多的灵活性,findAllByProductName和findAllProductInfoEntityByProductName方法没有什么区别.
**要查询的对象的类型是通过如何参数化 Repository 接口来决定的,而不是方法名称中的主题.**


不过,Spring Data 这个小型的DSL依旧有其局限性,有时候通过方法名表达预期的查询很繁琐,甚至无法实现.如果与呆这种情况,Spring Data能让我们通过#Query注解来解决问题

## 声明自定义查询

```java
 @Query("select p from ProductInfoEntity p where p.productName like '%米%' ")
   List<ProductInfoEntity> findProductInfo();
```

不过建议如果要写sql语句的话,我们还是用 mybatis 比较好.




















