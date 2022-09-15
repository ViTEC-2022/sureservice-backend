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
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final RequestRepository requestRepository;
    private final Validator validator;

    public RequestServiceImpl(ServiceRepository serviceRepository, EmployeeRepository employeeRepository, ClientRepository clientRepository, RequestRepository requestRepository, Validator validator) {
        this.serviceRepository = serviceRepository;
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
        var existingClient = requestRepository.findByClientId(clientId);

        if(existingClient.isEmpty())
            throw new ResourceNotFoundException("Client", clientId);

        return requestRepository.findByClientId(clientId);
    }

    @Override
    public Page<Request> getAllByClientId(Long clientId, Pageable pageable) {
        return requestRepository.findByClientId(clientId, pageable);
    }

    @Override
    public List<Request> getAllByEmployeeId(Long employeeId) {
        var existingEmployee = requestRepository.findByEmployeeId(employeeId);

        if(existingEmployee.isEmpty())
            throw new ResourceNotFoundException("Employee", employeeId);

        return requestRepository.findByEmployeeId(employeeId);
    }

    @Override
    public Page<Request> getAllByEmployeeId(Long employeeId, Pageable pageable) {
        return requestRepository.findByEmployeeId(employeeId, pageable);
    }

    @Override
    public Request create(Long clientId, Long employeeId, Long serviceId, Request request) {
        Set<ConstraintViolation<Request>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return serviceRepository.findById(serviceId).map(data -> {
            request.setService(data);
            return clientRepository.findById(clientId).map(client->{
                request.setClient(client);
                return employeeRepository.findById(employeeId).map(employee->{
                    request.setEmployee(employee);
                return requestRepository.save(request);
                }).orElseThrow(() -> new ResourceNotFoundException("Service", serviceId));
            }).orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        }).orElseThrow(() -> new ResourceNotFoundException("Employee", employeeId));
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
                                .withPaid(request.getPaid()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, requestId));
    }

    @Override
    public ResponseEntity<?> delete(Long requestId) {
        return requestRepository.findById(requestId).map(announcement -> {
            requestRepository.delete(announcement);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, requestId));
    }
}
