package com.sureservice.backend.client.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("clientMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ClientMapper clientMapper(){
        return new ClientMapper();
    }
}
