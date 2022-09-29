package com.sureservice.backend.service.service;

import com.sureservice.backend.service.domain.model.entity.Service;
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
public class ServiceServiceImplTest {

    @Mock
    Validator validator;

    @Mock
    Service service;

    @InjectMocks
    ServiceServiceImpl serviceService;

    @Mock
    ServiceRepository serviceRepository;


    @Before
    public void setUp() {
        service = new Service(2L,"Electricidad","url","Descripcion");
    }

    @Test
    public void getAll() {
        Mockito.when(serviceRepository.findAll()).thenReturn(Arrays.asList(service));
        assertNotNull(serviceService.getAll());
    }

    @Test
    public void getById() {
        Mockito.when(serviceRepository.findById(2L)).thenReturn(Optional.of(new Service()));
        assertNotNull(serviceService.getById(2L));
    }

    @Test
    public void create(){
        Mockito.when(serviceRepository.save(Mockito.any(Service.class))).thenReturn(service);
        assertNotNull(serviceService.create(new Service()));
    }
    /*
        @Test
        public void update() {
        Mockito.when(serviceRepository.save(Mockito.any(Service.class))).thenReturn(service);
        assertNotNull(serviceService.update(2L, new Service()));
        }
    */
    @Test
    public void delete() {
        Mockito.when(serviceRepository.findById(2L)).thenReturn(Optional.of(new Service()));
        assertNotNull(serviceService.delete(2L));
    }
}