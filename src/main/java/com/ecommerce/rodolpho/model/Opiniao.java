package com.ecommerce.rodolpho.model;

import com.ecommerce.rodolpho.controller.dto.OpiniaoDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(1) @Max(5)
    @Column(nullable = false)
    private Integer nota;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false, length = 500, columnDefinition = "TEXT")
    private String descricao;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    public Opiniao(){}

    public Opiniao(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public OpiniaoDto toResponse() {
        return new OpiniaoDto(id, nota, titulo, descricao, usuario.getUsername());
    }
}
