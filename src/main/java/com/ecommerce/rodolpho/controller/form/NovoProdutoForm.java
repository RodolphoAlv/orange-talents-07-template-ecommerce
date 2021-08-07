package com.ecommerce.rodolpho.controller.form;

import com.ecommerce.rodolpho.model.Caracteristica;
import com.ecommerce.rodolpho.model.Categoria;
import com.ecommerce.rodolpho.model.Produto;
import com.ecommerce.rodolpho.repository.CategoriaRepository;
import com.ecommerce.rodolpho.shared.IdExists;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NovoProdutoForm {
    @NotBlank
    private String nome;
    @NotNull
    @PositiveOrZero(message = "{produto.valor.negativo}")
    private BigDecimal valor;
    @NotNull
    @PositiveOrZero(message = "{produto.quantidade.negativa}")
    private Integer quantidade;
    @Valid
    @Size(min = 3, message = "{produto.caracteristicas}")
    private Set<CaracteristicaForm> caracteristicas;
    @NotBlank
    @Size(max = 1000, message = "{produto.descricao.longa}")
    private String descricao;
    @NotNull
    @Positive
    @IdExists(
            domainClass = Categoria.class,
            fieldName = "id",
            message = "{categoria.nao.existe}"
    )
    private Long categoriaId;

    public Produto toModel(CategoriaRepository categoriaRepository) {

        Categoria categoria = categoriaRepository.findById(categoriaId).get();
        Set<Caracteristica> caracteristicas = CaracteristicaForm.toModelList(this.caracteristicas);
        return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria);
    }
}
