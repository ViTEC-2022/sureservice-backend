package com.sureservice.backend.user.domain.service;

import com.sureservice.backend.security.domain.service.communication.AuthenticateRequest;
import com.sureservice.backend.security.domain.service.communication.RegisterRequest;
import com.sureservice.backend.user.domain.model.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    //get
    List<User> getAll();
    User getById(Long userId);

    //post, put, delete
    ResponseEntity<?> authenticate(AuthenticateRequest request);

    ResponseEntity<?> register(RegisterRequest request);
}
