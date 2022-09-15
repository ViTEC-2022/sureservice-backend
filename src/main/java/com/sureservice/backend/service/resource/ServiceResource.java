package com.sureservice.backend.service.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResource {
    private Long id;
    private String name;
    private String urlToImage;
    private String description;
}
