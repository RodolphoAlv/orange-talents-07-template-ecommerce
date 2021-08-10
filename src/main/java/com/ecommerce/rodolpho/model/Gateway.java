package com.ecommerce.rodolpho.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Gateway {

    PAGSEGURO(0){
        @Override
        public String urlPagamento(Long compraId, String baseUrl) {
            return "paypal.com?buyerId=" + compraId + "&redirectUrl=" + baseUrl + "/recibo";
        }
    },
    PAYPAL(1){
        @Override
        public String urlPagamento(Long compraId, String baseUrl) {
            return "pagseguro.com?returnId=" + compraId + "&redirectUrl=" + baseUrl + "/recibo";
        }
    };

    private final Integer codigo;

    Gateway(Integer codigo) {
        this.codigo = codigo;
    }

    public static Gateway toGateway(Integer codigo) {

        Optional<Gateway> gateway = Arrays.stream(Gateway.values())
                .filter(gate -> Objects.equals(gate.codigo, codigo))
                .findFirst();

        if(gateway.isPresent()) return gateway.get();

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Este código não pertence a nenhum gateway!"
        );
    }

    public abstract String urlPagamento(Long compraId, String baseUrl);
}
