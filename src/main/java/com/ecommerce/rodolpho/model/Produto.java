package com.ecommerce.rodolpho.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    @ManyToOne
    private Usuario dono;
    @Column(nullable = false)
    private LocalDateTime instante;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Imagem>imagens;

    @Deprecated
    public Produto() {}

    public Produto(
            String nome,
            BigDecimal valor,
            Integer quantidade,
            Set<Caracteristica> caracteristicas,
            String descricao,
            Categoria categoria,
            Usuario dono
    ) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.instante = LocalDateTime.now();
        this.dono = dono;
        this.imagens = new ArrayList<>();
    }

    public Produto adicionarImagens(List<String> links) {
        links.stream()
                .map(link -> new Imagem(link, this))
                .forEach(imagens::add);
        return this;
    }

    public Boolean isDono(Usuario usuario) {
        return this.dono.equals(usuario);
    }


}
