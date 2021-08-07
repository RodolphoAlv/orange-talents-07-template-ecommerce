package com.ecommerce.rodolpho.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    private Integer quantidade;
    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id")
    private Set<Caracteristica> caracteristicas;
    @Column(columnDefinition = "TEXT", length = 1000, nullable = false)
    private String descricao;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Categoria categoria;
    @Column(nullable = false)
    private LocalDateTime instante;

    public Produto(
            String nome,
            BigDecimal valor,
            Integer quantidade,
            Set<Caracteristica> caracteristicas,
            String descricao,
            Categoria categoria
    ) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.instante = LocalDateTime.now();
    }
}
