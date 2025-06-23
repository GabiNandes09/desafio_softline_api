package com.gabrielfernandes.Desafio_SoftLine.models.product;

import java.util.Objects;

/**
 * Classe utilitária responsável por converter objetos entre
 * {@link ProductModel}, {@link ProductRequestDTO} e {@link ProductResponseDTO}.
 *
 * <p>Ela centraliza a lógica de mapeamento entre as entidades persistentes e os
 * objetos de transferência de dados (DTOs), garantindo separação de responsabilidades
 * e facilitando a manutenção do código.</p>
 *
 * <p>Todos os métodos desta classe são estáticos e a classe não pode ser instanciada.</p>
 *
 * @author 
 */
public final class ProductMapper {

    /**
     * Construtor privado para evitar instanciação da classe utilitária.
     */
    private ProductMapper() {}

    /**
     * Converte um {@link ProductRequestDTO} em um {@link ProductModel}, que pode
     * ser usado para persistência no banco de dados.
     *
     * @param dto o DTO contendo os dados do produto recebidos da requisição
     * @return uma nova instância de {@link ProductModel} com os dados preenchidos
     * @throws NullPointerException se o DTO for {@code null}
     */
    public static ProductModel toEntity(ProductRequestDTO dto) {
        Objects.requireNonNull(dto, "DTO não pode ser nulo");
        ProductModel model = new ProductModel();
        model.setCode(dto.getCode());
        model.setBarCode(dto.getBarCode());
        model.setDescription(dto.getDescription());
        model.setPrice(dto.getPrice());
        model.setGrossWeight(dto.getGrossWeight());
        model.setNetWeight(dto.getNetWeight());
        return model;
    }

    /**
     * Converte um {@link ProductModel} em um {@link ProductResponseDTO}, que pode
     * ser retornado para o cliente na API.
     *
     * @param model a entidade {@link ProductModel} com os dados do produto
     * @return uma instância de {@link ProductResponseDTO} com os dados formatados para resposta
     */
    public static ProductResponseDTO toResponse(ProductModel model) {
        return new ProductResponseDTO(
                model.getId(),
                model.getCode(),
                model.getBarCode(),
                model.getDescription(),
                model.getPrice(),
                model.getGrossWeight(),
                model.getNetWeight()
        );
    }

    /**
     * Atualiza os campos de uma entidade {@link ProductModel} com os dados de um {@link ProductRequestDTO}.
     *
     * <p>Este método é utilizado em operações de atualização (update), mantendo a instância original da entidade
     * e apenas modificando os campos necessários.</p>
     *
     * @param entity a entidade que será atualizada
     * @param dto o DTO contendo os novos valores
     */
    public static void update(ProductModel entity, ProductRequestDTO dto) {
        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());
        entity.setBarCode(dto.getBarCode());
        entity.setPrice(dto.getPrice());
        entity.setGrossWeight(dto.getGrossWeight());
        entity.setNetWeight(dto.getNetWeight());
    }
}
