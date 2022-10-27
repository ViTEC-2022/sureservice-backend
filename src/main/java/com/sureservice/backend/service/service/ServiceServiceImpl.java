package com.sureservice.backend.service.service;

import com.sureservice.backend.service.domain.persistence.ServiceRepository;
import com.sureservice.backend.service.domain.service.ServiceService;
import com.sureservice.backend.shared.exception.ResourceNotFoundException;
import com.sureservice.backend.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class ServiceServiceImpl implements ServiceService {
    private static final String ENTITY = "Service";
    private final ServiceRepository serviceRepository;
    private final Validator validator;

    public ServiceServiceImpl(ServiceRepository serviceRepository, Validator validator) {
        this.serviceRepository = serviceRepository;
        this.validator = validator;
    }

    @Override
    public List<com.sureservice.backend.service.domain.model.entity.Service> getAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Page<com.sureservice.backend.service.domain.model.entity.Service> getAll(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public com.sureservice.backend.service.domain.model.entity.Service getById(Long serviceId) {
        return serviceRepository.findById(serviceId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,serviceId)) ;
    }

    @Override
    public com.sureservice.backend.service.domain.model.entity.Service create(com.sureservice.backend.service.domain.model.entity.Service service) {
        Set<ConstraintViolation<com.sureservice.backend.service.domain.model.entity.Service>> violations=validator.validate(service);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        return serviceRepository.save(service);
    }

    @Override
    public com.sureservice.backend.service.domain.model.entity.Service update(Long serviceId, com.sureservice.backend.service.domain.model.entity.Service request) {
        Set<ConstraintViolation<com.sureservice.backend.service.domain.model.entity.Service>> violations=validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        if(!serviceRepository.existsById(serviceId))
            throw new ResourceNotFoundException("Service", serviceId);

        return serviceRepository.findById(serviceId).map(user ->
                        serviceRepository.save(user.withName(request.getName())
                                .withDescription(request.getDescription())
                                .withUrlToImage(request.getUrlToImage())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,serviceId));
    }

    @Override
    public ResponseEntity<?> delete(Long serviceId) {
        return serviceRepository.findById(serviceId).map(data ->{
            serviceRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,serviceId));
    }

    @Override
    public void seed() {
        if(!serviceRepository.existsByName("Electriciy")) {
            serviceRepository.save((new com.sureservice.backend.service.domain.model.entity.Service())
                    .withName("Electriciy")
                    .withUrlToImage("https://i.ibb.co/J29j6BJ/electricianp.jpg")
                    .withDescription("Lorem ipsum dolor sit amet consectetur adipisicing elit. In vero amet fugiat " +
                            "repudiandae aut similique odit natus maxime praesentium."));
        }
        if(!serviceRepository.existsByName("Plumbing")) {
            serviceRepository.save((new com.sureservice.backend.service.domain.model.entity.Service())
                    .withName("Plumbing")
                    .withUrlToImage("https://i.ibb.co/26xs99Q/plumberp.jpg")
                    .withDescription("Lorem ipsum dolor sit amet consectetur adipisicing elit. In vero amet fugiat " +
                            "repudiandae aut similique odit natus maxime praesentium."));
        }
        if(!serviceRepository.existsByName("Computer repair")) {
            serviceRepository.save((new com.sureservice.backend.service.domain.model.entity.Service())
                    .withName("Computer repair")
                    .withUrlToImage("https://i.ibb.co/FzfGfGp/computer-repair.jpg")
                    .withDescription("Lorem ipsum dolor sit amet consectetur adipisicing elit. In vero amet fugiat " +
                            "repudiandae aut similique odit natus maxime praesentium."));
        }
    }
}
