package com.sureservice.backend.service.domain.model.entity;

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
@Table(name = "services")
public class Service extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max=100)
    private String name;

    @NotNull
    @NotBlank
    private String urlToImage;

    @NotNull
    @NotBlank
    @Size(max = 1000)
    private String description;
}
