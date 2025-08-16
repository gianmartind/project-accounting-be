package com.gmd.project_accounting_be.modules.purchase.entities;

import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "purchase")
public class Purchase {
    @Id
    @Column(name = "uuid")
    @UuidGenerator
    private String uuid;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "store_uuid")
    private String storeUuid;

    @Column(name = "notes")
    private String notes;
}
