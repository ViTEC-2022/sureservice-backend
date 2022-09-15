package com.sureservice.backend.security.domain.persistence;

import com.sureservice.backend.security.domain.model.entity.Role;
import com.sureservice.backend.security.domain.model.enumeration.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);

    boolean existsByName(Roles name);
}
