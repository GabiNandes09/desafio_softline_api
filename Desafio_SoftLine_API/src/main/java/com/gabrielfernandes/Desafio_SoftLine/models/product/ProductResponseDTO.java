package com.gabrielfernandes.Desafio_SoftLine.models.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    int id;
    int code;
    String barCode;
    String description;
    private float price;
    private float grossWeight;
    private float netWeight;
}
