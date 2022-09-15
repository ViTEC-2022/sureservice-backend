package com.sureservice.backend.employee.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("employeeMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public EmployeeMapper employeeMapper(){
        return new EmployeeMapper();
    }
}
