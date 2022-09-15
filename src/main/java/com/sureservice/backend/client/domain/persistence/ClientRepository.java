package com.sureservice.backend.client.domain.persistence;

import com.sureservice.backend.client.domain.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByUserId(Long userId);
}
