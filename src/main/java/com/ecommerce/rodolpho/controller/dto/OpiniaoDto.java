package com.ecommerce.rodolpho.controller.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OpiniaoDto {

    private Long id;
    private Integer nota;
    private String titulo;
    private String descricao;
    private String usuarioEmail;

    public OpiniaoDto(
            Long id,
            Integer nota,
            String titulo,
            String descricao,
            String usuarioEmail
    ) {
        this.id = id;
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarioEmail = usuarioEmail;
    }

    public Integer getNota() {
        return nota;
    }
}
