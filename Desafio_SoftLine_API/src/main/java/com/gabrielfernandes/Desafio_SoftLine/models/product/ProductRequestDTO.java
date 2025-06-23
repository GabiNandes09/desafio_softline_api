package com.gabrielfernandes.Desafio_SoftLine.models.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @Min(value = 1, message = "O código deve ser maior que zero")
    int code;

    @NotBlank(message = "O código de barras é obrigatório")
    String barCode;

    @NotBlank(message = "A descrição é obrigatória")
    String description;

    @PositiveOrZero(message = "O preço deve ser maior ou igual a 0")
    private float price;

    @PositiveOrZero(message = "O peso bruto deve ser maior ou igual a 0")
    private float grossWeight;

    @PositiveOrZero(message = "O peso líquido deve ser maior ou igual a 0")
    private float netWeight;
}
