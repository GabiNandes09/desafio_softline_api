package com.gabrielfernandes.Desafio_SoftLine.models.product;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "products")
@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false, unique = true)
    int code;

    @Column(nullable = false, unique = true)
    String barCode;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private float grossWeight;

    @Column(nullable = false)
    private float netWeight;

    LocalDateTime createdAt;
    int createdBy;

    LocalDateTime modifiedAt;
    int modifiedBy;

    LocalDateTime deletedAt;
    int deletedBy;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }
}
