package com.sureservice.backend.client.domain.service;

import com.sureservice.backend.client.domain.model.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    List<Client> getAll();
    Page<Client> getAll(Pageable pageable);
    Client getByUserId(Long userId);
    Client getById(Long id);
    Client create(Long userId,Client client);
    Client update(Long clientId, Client client);
    ResponseEntity<?> delete(Long clientId);
}
