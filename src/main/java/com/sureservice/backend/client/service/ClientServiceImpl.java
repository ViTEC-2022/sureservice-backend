package com.sureservice.backend.client.service;

import com.sureservice.backend.client.domain.model.entity.Client;
import com.sureservice.backend.client.domain.persistence.ClientRepository;
import com.sureservice.backend.client.domain.service.ClientService;
import com.sureservice.backend.shared.exception.ResourceNotFoundException;
import com.sureservice.backend.shared.exception.ResourceValidationException;
import com.sureservice.backend.user.domain.persistence.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    private static final String ENTITY = "Client";
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final Validator validator;

    public ClientServiceImpl(UserRepository userRepository, ClientRepository clientRepository, Validator validator) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.validator = validator;
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Page<Client> getAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client getByUserId(Long userId) {
        return clientRepository.findByUserId(userId);
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,id));
    }

    @Override
    public Client create(Long userId, Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(data -> {
            client.setUser(data);
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    @Override
    public Client update(Long clientId, Client client) {
        Set<ConstraintViolation<Client>> violations=validator.validate(client);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        if(!clientRepository.existsById(clientId))
            throw new ResourceNotFoundException("Client", clientId);

        return clientRepository.findById(clientId).map(data ->
                        clientRepository.save(data.withAddress(client.getAddress())
                                .withAge(client.getAge())
                                .withAltphone(client.getAltphone())
                                .withDescription(client.getDescription())
                                .withPhone(client.getPhone())
                                .withUrlToImage(client.getUrlToImage())
                                ))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, clientId));
    }

    @Override
    public ResponseEntity<?> delete(Long employeeId) {
        return clientRepository.findById(employeeId).map(data -> {
            clientRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, employeeId));
    }
}
