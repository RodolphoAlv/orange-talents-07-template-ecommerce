package com.ecommerce.rodolpho.shared;

import org.mindrot.jbcrypt.BCrypt;

public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(String senha) {
        this.senha = senha;
    }

    public String encode() {
        return BCrypt.hashpw(senha, BCrypt.gensalt(10));
    }
}
