package com.sureservice.backend.service.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("serviceMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ServiceMapper serviceMapper(){
        return new ServiceMapper();
    }
}
