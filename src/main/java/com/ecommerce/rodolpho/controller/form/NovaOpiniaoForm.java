package com.ecommerce.rodolpho.controller.form;

import com.ecommerce.rodolpho.model.Opiniao;
import com.ecommerce.rodolpho.model.Produto;
import com.ecommerce.rodolpho.model.Usuario;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NovaOpiniaoForm {
    @NotNull
    @PositiveOrZero
    @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;

    public Opiniao toModel(Produto produto, Usuario usuarioLogado) {
        return new Opiniao(nota, titulo, descricao, usuarioLogado, produto);
    }
}
