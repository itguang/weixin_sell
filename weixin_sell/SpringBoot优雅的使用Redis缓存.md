# SpringBoot优雅的使用Redis缓存


## 第一步: Spring Boot 启动类上添加 @EnableCaching 注解


## 第二步: 在需要缓存的方法上添加  @Cacheable(cacheNames = "order",key = "123") 注解,可以进行结果的缓存

## 第三步: 在需要失效缓存的方法上添加 @CacheEvict(cacheNames = "product",key = "123") 注解,可以失效缓存 


# 几个注解的使用 ?????????????????????????????????????????????????

  [注] 如果没有 key ,默认是方法的参数

**动态key**

  @CachePut(cacheNames = "product",key = "123")
    @CacheEvict(cacheNames = "product",key = "#id")
    public void test (String id){


    }
     @CacheEvict(cacheNames = "product",key = "#id",condition = "#id.length()>3")
        public void test (String id){
    
    
        }


unless 用法: 只缓存结果==0的

```java
@Cacheable(cacheNames = "product",key = "123",unless = "#result.getCode()!=0")
    public ResultOV list() {}

```
 



















































