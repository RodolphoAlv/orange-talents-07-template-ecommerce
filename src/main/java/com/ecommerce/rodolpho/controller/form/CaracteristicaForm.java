package com.ecommerce.rodolpho.controller.form;

import com.ecommerce.rodolpho.model.Caracteristica;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

public class CaracteristicaForm {
    @NotBlank
    private String nome;
    @NotBlank
    @Size(max = 1000)
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Set<Caracteristica>toModelList(Set<CaracteristicaForm> dto) {
        return dto
                .stream()
                .map(caracteristica -> new Caracteristica(
                        caracteristica.getNome(),
                        caracteristica.getDescricao()
                ))
                .collect(Collectors.toSet());
    }

}
