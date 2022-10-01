package com.sureservice.backend.client.service;

import com.sureservice.backend.client.domain.model.entity.Client;
import com.sureservice.backend.client.domain.persistence.ClientRepository;
import com.sureservice.backend.service.domain.model.entity.Service;
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
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
public class ClientServiceImplTest {
    @Mock
    Validator validator;

    @Mock
    ClientRepository clientRepository;

    @Mock
    Client client;

    @InjectMocks
    ClientServiceImpl clientService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setup(){
        User user = new User();
        client=new Client(1L,"Carlitos",22,"123123123","12345","-","-","-",user);
    }

    @Test
    public void getAll() {
        Mockito.when(clientRepository.findAll()).thenReturn(Arrays.asList(client));
        assertNotNull(clientService.getAll());
    }

    @Test
    public void getByUserId() {
        Mockito.when(clientRepository.findByUserId(1L)).thenReturn(client);
        assertNotNull(clientService.getByUserId(1L));
    }

    @Test
    public void getById() {
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(new Client()));
        assertNotNull(clientService.getById(1L));
    }

    @Test
    public void create() {
        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        assertNotNull(clientService.create(1L,new Client()));
    }

    /*
    @Test
    public void update() {
        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(new Client()));
        assertNotNull(clientService.update(1L,new Client()));
    }
    */

    @Test
    public void delete() {
        Mockito.when(clientRepository.findById(2L)).thenReturn(Optional.of(new Client()));
        assertNotNull(clientService.delete(2L));
    }
}