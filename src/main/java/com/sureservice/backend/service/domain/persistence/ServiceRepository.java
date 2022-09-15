package com.sureservice.backend.service.domain.persistence;

import com.sureservice.backend.service.domain.model.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Service findByName(String name);
}
