package com.ecommerce.rodolpho.controller.form;

import com.ecommerce.rodolpho.model.Compra;
import com.ecommerce.rodolpho.model.Gateway;
import com.ecommerce.rodolpho.model.Produto;
import com.ecommerce.rodolpho.model.Usuario;
import com.ecommerce.rodolpho.shared.IdExists;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CompraForm {
    @NotNull
    private Integer codigoGateway;

    @IdExists(
            domainClass = Produto.class,
            fieldName = "id",
            message = "{produto.nao.existe}"
    )
    private Long produtoId;
    @NotNull
    @Positive
    private Integer quantidade;

    public Gateway getGateway() {
        return Gateway.toGateway(codigoGateway);
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Compra toModel(Produto produto, Usuario usuario) {
        return new Compra(getGateway(), produto, quantidade, usuario);
    }
}
