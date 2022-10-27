package com.sureservice.backend.client.service;

import com.sureservice.backend.client.domain.model.entity.Client;
import com.sureservice.backend.client.domain.persistence.ClientRepository;
import com.sureservice.backend.user.domain.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validator;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Mock
    private ClientRepository clientRepository;

    //@Mock
    //Validator validator;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;

    @BeforeEach
    void setup() {
        User user = new User();
        client=new Client(1L,"Carlitos",22,"123123123","12345","-","-","-",user);
    }


    @DisplayName("Test para listar los clientes")
    @Test
    void getAll() {
        User user = new User();
        Client client2 = new Client(2L,"Carlitos",22,"123123123","12345","-","-","-",user);
        given(clientRepository.findAll()).willReturn(List.of(client,client2));

        //when
        List<Client> clients = clientService.getAll();

        //then
        assertThat(clients).isNotNull();
        assertThat(clients.size()).isEqualTo(2);

    }
}