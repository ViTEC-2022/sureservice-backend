package com.sureservice.backend.request.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateRequestResource {
    @NotNull
    @NotBlank
    @Size(max=200)
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotNull
    @NotBlank
    private String urlToImage;
}
