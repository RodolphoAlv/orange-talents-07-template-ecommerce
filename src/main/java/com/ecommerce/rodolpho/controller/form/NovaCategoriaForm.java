package com.ecommerce.rodolpho.controller.form;

import com.ecommerce.rodolpho.model.Categoria;
import com.ecommerce.rodolpho.repository.CategoriaRepository;
import com.ecommerce.rodolpho.shared.OptionalIdExists;
import com.ecommerce.rodolpho.shared.UniqueValue;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NovaCategoriaForm {

    @NotBlank
    @UniqueValue(
            domainClass = Categoria.class,
            fieldName = "nome",
            message = "{categoria.ja.cadastrada}"
    )
    private String nome;

    @Positive
    @OptionalIdExists(
            domainClass = Categoria.class,
            fieldName = "id",
            message = "{categoria.nula.ou.valida}"
    )
    private Long categoriaMaeId;

    public Categoria toModel(CategoriaRepository categoriaRepository) {

        if(categoriaMaeId != null) {
            return categoriaRepository
                    .findById(categoriaMaeId)
                    .map(categoria -> new Categoria(nome, categoria))
                    .orElseGet(() -> new Categoria(nome));
        }

        return new Categoria(nome);
    }
}
