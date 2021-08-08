package com.ecommerce.rodolpho.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @JsonIgnore
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Imagem() {}

    public Imagem(String url, Produto produto) {
        this.url = url;
        this.produto = produto;
    }
}
