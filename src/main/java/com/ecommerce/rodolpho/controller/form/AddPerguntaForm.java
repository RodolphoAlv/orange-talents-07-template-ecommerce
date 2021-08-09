package com.ecommerce.rodolpho.controller.form;

import com.ecommerce.rodolpho.model.Opiniao;
import com.ecommerce.rodolpho.model.Pergunta;
import com.ecommerce.rodolpho.model.Produto;
import com.ecommerce.rodolpho.model.Usuario;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AddPerguntaForm {

    @NotBlank
    private String titulo;

    public Pergunta toModel(Usuario usuarioLogado, Produto produto) {
        return new Pergunta(titulo, usuarioLogado, produto);
    }
}
