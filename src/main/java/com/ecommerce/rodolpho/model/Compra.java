package com.ecommerce.rodolpho.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Gateway gateway;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;
    @Positive
    @Column(nullable = false)
    private Integer quantidade;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario comprador;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Deprecated
    public Compra() {}

    public Compra(
            Gateway gateway,
            Produto produto,
            Integer quantidade,
            Usuario comprador
    ) {
        this.gateway = gateway;
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.status = Status.INICIADO;
    }

    public Long getId() {
        return id;
    }
}
