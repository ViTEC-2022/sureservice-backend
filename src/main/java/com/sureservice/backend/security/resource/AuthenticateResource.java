package com.sureservice.backend.security.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthenticateResource {
    private Long id;
    private String email;
    private List<String> roles;
    private String token;
}
