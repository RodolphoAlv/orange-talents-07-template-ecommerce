package com.ecommerce.rodolpho.controller.form;

import com.ecommerce.rodolpho.model.Usuario;
import com.ecommerce.rodolpho.shared.SenhaLimpa;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NovoUsuarioForm {

    @NotBlank(message = "{login.nulo}")
    @Email(message = "{login.email}")
    private String login;

    @NotBlank(message = "{senha.nula}")
    @Size(min = 6, message = "{senha.curta}")
    private String senha;

    public Usuario toModel() {
        SenhaLimpa senhaLimpa = new SenhaLimpa(senha);
        return new Usuario(login, senhaLimpa);
    }
}
