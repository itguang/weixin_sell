package com.itguang.weixinsell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author itguang
 * @create 2017-12-01 14:51
 **/
@Component
@ConfigurationProperties("projectUrl")
@Data
public class ProjectUrlConfig {


    /**
     * 项目部署域名
     */
    private String domain;

}
