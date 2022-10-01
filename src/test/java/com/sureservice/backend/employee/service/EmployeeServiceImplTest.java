package com.sureservice.backend.employee.service;

import com.sureservice.backend.client.domain.model.entity.Client;
import com.sureservice.backend.employee.domain.model.entity.Employee;
import com.sureservice.backend.employee.domain.persistence.EmployeeRepository;
import com.sureservice.backend.service.domain.model.entity.Service;
import com.sureservice.backend.service.domain.persistence.ServiceRepository;
import com.sureservice.backend.user.domain.model.entity.User;
import com.sureservice.backend.user.domain.persistence.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validator;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
    @Mock
    Validator validator;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    Employee employee;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    UserRepository userRepository;

    @Mock
    ServiceRepository serviceRepository;

    @Before
    public void setup(){
        User user = new User();
        Service service = new Service();
        employee=new Employee(1L,"Carlitos",22,"123123123","12345","-","-",service,user);
    }

    @Test
    public void getAll() {
        Mockito.when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
        assertNotNull(employeeService.getAll());
    }


    @Test
    public void getById() {
        Mockito.when(employeeRepository.findByUserId(1L)).thenReturn(employee);
        assertNotNull(employeeService.getById(1L));
    }

    @Test
    public void create() {
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        Mockito.when(serviceRepository.findById(1l)).thenReturn(Optional.of(new Service()));
        assertNotNull(employeeService.create(1L,1L,new Employee()));
    }

    @Test
    public void delete() {
        Mockito.when(employeeRepository.findById(2L)).thenReturn(Optional.of(new Employee()));
        assertNotNull(employeeService.delete(2L));
    }

    @Test
    public void getAllByServiceId() {
        Mockito.when(employeeRepository.findAllByServiceId(1L)).thenReturn(Arrays.asList(employee));
        assertNotNull(employeeService.getAllByServiceId(1L));
    }

    @Test
    public void getByServiceId() {
        Mockito.when(employeeRepository.findByServiceId(1L)).thenReturn(employee);
        assertNotNull(employeeService.getByServiceId(1L));
    }
}