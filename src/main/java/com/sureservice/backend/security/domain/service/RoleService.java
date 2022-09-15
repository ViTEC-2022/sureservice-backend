package com.sureservice.backend.security.domain.service;

import com.sureservice.backend.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {
    void seed();

    List<Role> getAll();
}
