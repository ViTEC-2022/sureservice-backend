package com.sureservice.backend.user.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateUserResource {
    @NotNull
    @NotBlank
    @Size(max = 40)
    private String email;
    @NotNull
    @NotBlank
    private String typeuser;

    @NotNull
    @NotBlank
    @Size(max=50)
    private String password;
}
