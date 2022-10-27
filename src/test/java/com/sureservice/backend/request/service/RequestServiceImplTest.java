package com.sureservice.backend.request.service;

import com.sureservice.backend.client.domain.model.entity.Client;
import com.sureservice.backend.client.domain.persistence.ClientRepository;
import com.sureservice.backend.employee.domain.model.entity.Employee;
import com.sureservice.backend.employee.domain.persistence.EmployeeRepository;
import com.sureservice.backend.request.domain.model.entity.Request;
import com.sureservice.backend.request.domain.persistence.RequestRepository;
import com.sureservice.backend.service.domain.persistence.ServiceRepository;
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
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class RequestServiceImplTest {

    @Mock
    Validator validator;

    @Mock
    Request request;

    @Mock
    RequestRepository requestRepository;

    @InjectMocks
    RequestServiceImpl requestService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    ServiceRepository serviceRepository;

    @Before
    public void setUp() {
        Employee employee= new Employee();
        Client client= new Client();
        request= new Request(1L,"Servicido de Carlitos","Servicio","-",false,100,false,employee,client);
    }

    @Test
    public void getById() {
        Mockito.when(requestRepository.findById(1L)).thenReturn(Optional.of(new Request()));
        assertNotNull(requestService.getById(1L));
    }

    @Test
    public void getAll() {
        Mockito.when(requestRepository.findAll()).thenReturn(Arrays.asList(request));
        assertNotNull(requestService.getAll());
    }

    @Test
    public void getAllByClientId() {
        Mockito.when(requestRepository.findByClientId(1L)).thenReturn(Arrays.asList(request));
        assertNotNull(requestService.getAllByClientId(1L));
    }

    @Test
    public void getAllByEmployeeId() {
        Mockito.when(requestRepository.findByEmployeeId(1L)).thenReturn(Arrays.asList(request));
        assertNotNull(requestService.getAllByEmployeeId(1L));
    }

    @Test
    public void create() {
        Mockito.when(requestRepository.save(Mockito.any(Request.class))).thenReturn(request);
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee()));
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(new Client()));
        assertNotNull(requestService.create(1L,1L,new Request()));
    }

    @Test
    public void delete() {
        Mockito.when(requestRepository.findById(2L)).thenReturn(Optional.of(new Request()));
        assertNotNull(requestService.delete(2L));
    }
}