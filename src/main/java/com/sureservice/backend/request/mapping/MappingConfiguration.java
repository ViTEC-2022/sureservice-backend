package com.sureservice.backend.request.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("requestMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public RequestMapper requestMapper(){
        return new RequestMapper();
    }
}
