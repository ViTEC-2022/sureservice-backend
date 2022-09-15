package com.sureservice.backend.security.mapping;

import com.sureservice.backend.user.mapping.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public RoleMapper roleMapper() {
        return new RoleMapper();
    }
    @Bean
    public UserMapper userMapper(){
        return new UserMapper();
    }
}
