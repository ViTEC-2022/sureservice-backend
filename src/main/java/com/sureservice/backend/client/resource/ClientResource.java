package com.sureservice.backend.client.resource;

import com.sureservice.backend.user.resource.UserResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResource {
    private Long id;
    private String name;
    private int age;
    private String phone;
    private String altphone;
    private String urlToImage;
    private String address;
    private String description;
    private UserResource user;
}
