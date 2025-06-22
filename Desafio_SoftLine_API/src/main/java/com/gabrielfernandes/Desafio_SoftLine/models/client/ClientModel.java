package com.gabrielfernandes.Desafio_SoftLine.models.client;

import com.gabrielfernandes.Desafio_SoftLine.models.address.AddressModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "clients")
@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String fantasyName;

    @Column(nullable = false, unique = true, length = 14)
    String document;

    @ManyToOne
    @JoinColumn(name = "id_address", nullable = false)
    AddressModel address;

    public ClientModel(String name, String fantasyName, String document, AddressModel address) {
        this.name = name;
        this.fantasyName = fantasyName;
        this.document = document;
        this.address = address;
    }

    
}
