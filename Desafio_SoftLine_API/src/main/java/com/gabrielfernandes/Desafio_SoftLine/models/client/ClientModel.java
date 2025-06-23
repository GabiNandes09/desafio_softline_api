package com.gabrielfernandes.Desafio_SoftLine.models.client;

import java.time.LocalDateTime;

import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "clients")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "O nome fantasia é obrigatório")
    @Column(nullable = false)
    private String fantasyName;

    @NotBlank(message = "O CNPJ é obrigatório")
    @Size(min = 14, max = 14, message = "O CNPJ deve conter exatamente 14 dígitos")
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter apenas números")
    @Column(nullable = false, unique = true, length = 14)
    private String document;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address", referencedColumnName = "id", nullable = false)
    private AddressModel address;

    private LocalDateTime createdAt;
    private int createdBy;

    private LocalDateTime modifiedAt;
    private int modifiedBy;

    private LocalDateTime deletedAt;
    private int deletedBy;

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
