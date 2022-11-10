package com.sureservice.backend.request.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sureservice.backend.client.domain.model.entity.Client;
import com.sureservice.backend.employee.domain.model.entity.Employee;
import com.sureservice.backend.service.domain.model.entity.Service;
import com.sureservice.backend.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requests")
public class Request extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private Boolean paid;

    private int price;

    private Boolean confirmation;

    private Boolean done;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name= "employee_id", nullable = false)
    @JsonIgnore
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name= "client_id", nullable = false)
    @JsonIgnore
    private Client client;
}
