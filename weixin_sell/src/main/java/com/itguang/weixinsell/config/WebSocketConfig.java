package com.itguang.weixinsell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author itguang
 * @create 2017-12-01 16:46
 **/
//@Component
public class WebSocketConfig {


//    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
       return new ServerEndpointExporter();
    }

}
