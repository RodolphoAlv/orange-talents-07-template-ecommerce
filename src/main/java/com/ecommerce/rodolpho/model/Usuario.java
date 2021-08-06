package com.ecommerce.rodolpho.model;

import com.ecommerce.rodolpho.shared.SenhaLimpa;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    public Usuario(String login, SenhaLimpa senhaLimpa) {
        this.dataHora = LocalDateTime.now();
        this.login = login;
        this.senha = senhaLimpa.encode();
    }
}
