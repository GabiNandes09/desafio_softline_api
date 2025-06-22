package com.gabrielfernandes.Desafio_SoftLine.models.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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

    @Min(value = 1, message = "O código deve ser maior que zero")
    @Column(nullable = false, unique = true)
    int code;

    @NotBlank(message = "O código de barras é obrigatório")
    @Column(nullable = false, unique = true)
    String barCode;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    @PositiveOrZero(message = "O preço deve ser maior ou igual a 0")
    private float price;

    @Column(nullable = false)
    @PositiveOrZero(message = "O peso bruto deve ser maior ou igual a 0")
    private float grossWeight;

    @Column(nullable = false)
    @PositiveOrZero(message = "O peso líquido deve ser maior ou igual a 0")
    private float netWeight;
}
