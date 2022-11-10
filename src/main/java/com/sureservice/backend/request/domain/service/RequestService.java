package com.sureservice.backend.request.domain.service;

import com.sureservice.backend.request.domain.model.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RequestService {
    Request getById(Long requestId);
    List<Request> getAll();
    List<Request> getAllByClientId(Long clientId);
    List<Request> getAllByEmployeeId(Long employeeId);
    List<Request> getAllByDoneAndEmployeeIdAndPaid(Boolean done, Long employeeId, Boolean paid);
    List<Request> getAllByPaidAndEmployeeId(Boolean paid, Long employeeId);
    List<Request> getAllByPaidAndClientId(Boolean paid, Long clientId);
    //post, put, delete
    Request create(Long clientId, Long employeeId, Request request);
    Request update(Long requestId, Request request);
    ResponseEntity<?> delete(Long requestId);
}
