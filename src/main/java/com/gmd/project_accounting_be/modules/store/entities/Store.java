package com.gmd.project_accounting_be.modules.store.entities;


import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store")
public class Store {
    @Id
    @Column(name = "uuid", length = 36)
    @UuidGenerator
    private String uuid;

    @NotNull
    @Column(name = "name", nullable = false, unique = true, length = 32)
    private String name;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "notes", length = 255)
    private String notes;
}
