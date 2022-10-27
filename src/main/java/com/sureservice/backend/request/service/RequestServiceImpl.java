package com.sureservice.backend.request.service;

import com.sureservice.backend.client.domain.persistence.ClientRepository;
import com.sureservice.backend.employee.domain.persistence.EmployeeRepository;
import com.sureservice.backend.request.domain.model.entity.Request;
import com.sureservice.backend.request.domain.persistence.RequestRepository;
import com.sureservice.backend.request.domain.service.RequestService;
import com.sureservice.backend.service.domain.persistence.ServiceRepository;
import com.sureservice.backend.shared.exception.ResourceNotFoundException;
import com.sureservice.backend.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


import java.util.List;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {
    private static final String ENTITY = "Request";
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final RequestRepository requestRepository;
    private final Validator validator;

    public RequestServiceImpl(EmployeeRepository employeeRepository, ClientRepository clientRepository, RequestRepository requestRepository, Validator validator) {
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
        this.requestRepository = requestRepository;
        this.validator = validator;
    }

    @Override
    public Request getById(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,requestId));
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> getAllByClientId(Long clientId) {
        var existingClient = clientRepository.findById(clientId);

        if(existingClient.isEmpty())
            throw new ResourceNotFoundException("Client", clientId);

        return requestRepository.findByClientId(clientId);
    }

    @Override
    public List<Request> getAllByEmployeeId(Long employeeId) {
        var existingEmployee = employeeRepository.findById(employeeId);

        if(existingEmployee.isEmpty())
            throw new ResourceNotFoundException("Employee", employeeId);

        return requestRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Request> getAllByConfirmationAndEmployeeId(Boolean confirmation, Long employeeId) {
        var existingEmployee = requestRepository.findByEmployeeId(employeeId);

        if(existingEmployee.isEmpty())
            throw new ResourceNotFoundException("Employee", employeeId);

        return requestRepository.findByConfirmationAndEmployeeId(confirmation,employeeId);
    }

    @Override
    public List<Request> getAllByPaidAndEmployeeId(Boolean paid, Long employeeId) {
        var existingEmployee = requestRepository.findByEmployeeId(employeeId);

        if(existingEmployee.isEmpty())
            throw new ResourceNotFoundException("Employee", employeeId);

        return requestRepository.findByPaidAndEmployeeId(paid,employeeId);
    }

    @Override
    public List<Request> getAllByPaidAndClientId(Boolean paid, Long clientId) {
        var existingClient = clientRepository.findById(clientId);

        if(existingClient.isEmpty())
            throw new ResourceNotFoundException("Client", clientId);

        return requestRepository.findByPaidAndClientId(paid,clientId);
    }

    @Override
    public Request create(Long clientId, Long employeeId, Request request) {
        Set<ConstraintViolation<Request>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        request.setPrice(0);
        request.setConfirmation(false);
        request.setPaid(false);

        return clientRepository.findById(clientId).map(client->{
            request.setClient(client);
            return employeeRepository.findById(employeeId).map(employee->{
                request.setEmployee(employee);
                    return requestRepository.save(request);
                }).orElseThrow(() -> new ResourceNotFoundException("Service", employeeId));
            }).orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
    }

    @Override
    public Request update(Long requestId, Request request) {
        Set<ConstraintViolation<Request>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return requestRepository.findById(requestId).map(data ->
                requestRepository.save(
                        data.withTitle(request.getTitle())
                                .withDescription(request.getDescription())
                                .withUrlToImage(request.getUrlToImage())
                                .withPaid(request.getPaid())
                                .withPrice(request.getPrice())
                                .withConfirmation(request.getConfirmation())
                                ))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, requestId));
    }

    @Override
    public ResponseEntity<?> delete(Long requestId) {
        return requestRepository.findById(requestId).map(announcement -> {
            requestRepository.delete(announcement);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, requestId));
    }
}
