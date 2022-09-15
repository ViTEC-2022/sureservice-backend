package com.sureservice.backend.request.domain.persistence;

import com.sureservice.backend.request.domain.model.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
    List<Request> findByClientId(Long clientId);
    Page<Request> findByClientId(Long clientId, Pageable pageable);
    List<Request> findByEmployeeId(Long employeeId);
    Page<Request> findByEmployeeId(Long employeeId, Pageable pageable);
}
