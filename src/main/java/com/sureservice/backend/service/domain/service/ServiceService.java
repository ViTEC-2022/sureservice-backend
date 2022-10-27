package com.sureservice.backend.service.domain.service;


import com.sureservice.backend.service.domain.model.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceService {
    List<Service> getAll();
    Page<Service> getAll(Pageable pageable);
    Service getById(Long serviceId);

    //post, put, delete
    Service create(Service service);
    Service update(Long serviceId, Service request);
    ResponseEntity<?> delete(Long serviceId);

    void seed();
}
