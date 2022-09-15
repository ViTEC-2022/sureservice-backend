package com.sureservice.backend.user.api;

import com.sureservice.backend.security.domain.service.communication.AuthenticateRequest;
import com.sureservice.backend.security.domain.service.communication.RegisterRequest;
import com.sureservice.backend.user.domain.service.UserService;
import com.sureservice.backend.user.mapping.UserMapper;
import com.sureservice.backend.user.resource.UserResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SecurityRequirement(name = "acme")
@Tag(name = "Users", description = "Create, read, update and delete users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UserService userService;
    private final UserMapper mapper;

    public UsersController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.mapper = userMapper;
    }

    @GetMapping
    public List<UserResource> getAllUsers(){
        return mapper.modelListToResource(userService.getAll());
    }

    @GetMapping("{userId}")
    public UserResource getUserById(@PathVariable("userId") Long userId){
        return mapper.toResource(userService.getById(userId));
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateRequest request) {
        return userService.authenticate(request);
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    /*
    @PutMapping("{userId}")
    private UserResource updateUser(@PathVariable("userId") Long userId,@RequestBody UpdateUserResource resource){
        return mapper.toResource(userService.update(userId,mapper.toModel(resource)));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId){
        return userService.delete(userId);
    }
    */
}
