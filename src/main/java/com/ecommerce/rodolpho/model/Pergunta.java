package com.ecommerce.rodolpho.model;

import com.ecommerce.rodolpho.controller.dto.PerguntaDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private LocalDateTime instante;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario interessado;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @Deprecated
    public Pergunta() {}

    public Pergunta(String titulo, Usuario interessado, Produto produto) {
        this.titulo = titulo;
        this.instante = LocalDateTime.now();
        this.interessado = interessado;
        this.produto = produto;
    }

    public PerguntaDto toResponse() {
        return new PerguntaDto(id, titulo, instante, interessado.getUsername(), produto.getId());
    }
}
