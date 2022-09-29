package com.sureservice.backend.user.service;

import com.sureservice.backend.security.domain.model.entity.Role;
import com.sureservice.backend.security.domain.model.enumeration.Roles;
import com.sureservice.backend.security.domain.persistence.RoleRepository;
import com.sureservice.backend.security.domain.service.communication.AuthenticateRequest;
import com.sureservice.backend.security.domain.service.communication.RegisterRequest;
import com.sureservice.backend.service.domain.model.entity.Service;
import com.sureservice.backend.user.domain.model.entity.User;
import com.sureservice.backend.user.domain.persistence.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validator;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Mock
    Validator validator;

    @Mock
    User user;

    @Mock
    RegisterRequest registerRequest;

    @Mock
    AuthenticateRequest authenticateRequest;

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Before
    public void setUp() {
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L, Roles.ROLE_CLIENT);
        user= new User(2L,"carlos@gmail.com","password",roles);
    }

    @Test
    public void authenticate() {

    }

    @Test
    public void register() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        assertNotNull(userService.register(new RegisterRequest()));
    }
}