package com.ecommerce.rodolpho.controller.form;

import com.ecommerce.rodolpho.model.Categoria;
import com.ecommerce.rodolpho.shared.UniqueValue;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NovaCategoriaForm {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @Positive
    private Long categoriaMaeId;

    public Long getCategoriaMaeId() {
        return  this.categoriaMaeId;
    }

    public Categoria toModel(Optional<Categoria> possivelCategoria) {

        return possivelCategoria.map(categoria -> new Categoria(nome, categoria))
                .orElseGet(() -> new Categoria(nome));
    }
}
