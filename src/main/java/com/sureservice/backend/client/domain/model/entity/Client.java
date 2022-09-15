package com.sureservice.backend.client.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sureservice.backend.shared.domain.model.AuditModel;
import com.sureservice.backend.user.domain.model.entity.User;
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
@Table(name = "clients")
public class Client extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max=100)
    private String name;

    private int age;

    @NotNull
    @NotBlank
    @Size(max = 9)
    private String phone;

    @NotNull
    @NotBlank
    @Size(max = 5)
    private String altphone;

    @NotNull
    @NotBlank
    private String urlToImage;

    @NotNull
    @NotBlank
    @Size(max = 500)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 1000)
    private String description;


    //Relationship
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name= "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
