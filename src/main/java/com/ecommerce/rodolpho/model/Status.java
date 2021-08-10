package com.ecommerce.rodolpho.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Status {
    INICIADO(0),
    PENDENTE(1),
    PAGO(2);

    private final Integer codigo;

    Status(Integer codigo) {
        this.codigo = codigo;
    }

    public static Status toStatus(Integer codigo) {
        Optional<Status> status = Arrays.stream(Status.values())
                .filter(stat -> Objects.equals(stat.codigo, codigo))
                .findAny();
        if(status.isPresent()) {
            return status.get();
        }

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Este código não pertence a nenhum status"
        );
    }
}
