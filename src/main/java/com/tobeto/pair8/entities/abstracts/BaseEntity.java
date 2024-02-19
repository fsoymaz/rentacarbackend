package com.tobeto.pair8.entities.abstracts;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name="updated_date",nullable = true)
    private LocalDate updatedDate;

    @PrePersist
    private void beforeAdd() {
        createdDate = LocalDate.now();
    }

    @PreUpdate
    private void beforeUpdate() {
        updatedDate = LocalDate.now();
    }
}
