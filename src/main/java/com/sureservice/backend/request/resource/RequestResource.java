package com.sureservice.backend.request.resource;

import com.sureservice.backend.client.resource.ClientResource;
import com.sureservice.backend.employee.resource.EmployeeResource;
import com.sureservice.backend.service.resource.ServiceResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestResource {
    private Long id;
    private String title;
    private String description;
    private String urlToImage;
    private Boolean paid;
    private int price;
    private Boolean confirmation;
    private ClientResource client;
    private EmployeeResource employee;
}
