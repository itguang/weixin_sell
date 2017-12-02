# Spring Boot 快速使用 Mybatis

## 第一步:引入依赖

```xml
<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.1</version>
		</dependency>
```

## 第二步:开启 mapper.java 扫描

```java
@SpringBootApplication
@MapperScan("com.itguang.weixinsell.dao.mapper")
public class WeixinSellApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeixinSellApplication.class, args);
	}
}
```


## 第三步开启 mapper.xml 扫描

```yaml
# 配置mybatis的mapper.xml文件扫描路径
mybatis:
  mapper-locations: classpath:mapper/*.xml
```

# ok




























