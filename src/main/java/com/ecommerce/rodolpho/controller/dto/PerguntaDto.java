package com.ecommerce.rodolpho.controller.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PerguntaDto {

    private Long id;
    private String titulo;
    private LocalDateTime instante;
    private String interessadoEmail;
    private Long produtoId;

    public PerguntaDto(
            Long id,
            String titulo,
            LocalDateTime instante,
            String interessadoEmail,
            Long produtoId
    ) {
        this.id = id;
        this.titulo = titulo;
        this.instante = instante;
        this.interessadoEmail = interessadoEmail;
        this.produtoId = produtoId;
    }
}
