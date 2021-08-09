package com.ecommerce.rodolpho.controller.dto;

import com.ecommerce.rodolpho.model.Caracteristica;
import com.ecommerce.rodolpho.model.Produto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DetalheProdutoDto {

    private List<String>links;
    private String nome;
    private BigDecimal preco;
    private Set<Caracteristica> caracteristicas;
    private String descricao;
    private Integer mediaNotas;
    private Integer quantidadeNotas;
    private List<OpiniaoDto> opinioes;
    private List<PerguntaDto> perguntas;

    public DetalheProdutoDto(
            List<String> links,
            String nome,
            BigDecimal preco,
            Set<Caracteristica> caracteristicas,
            String descricao, Integer mediaNotas,
            Integer quantidadeNotas,
            List<OpiniaoDto> opinioes,
            List<PerguntaDto> perguntas
    ) {
        this.links = links;
        this.nome = nome;
        this.preco = preco;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.mediaNotas = mediaNotas;
        this.quantidadeNotas = quantidadeNotas;
        this.opinioes = opinioes;
        this.perguntas = perguntas;
    }

    public static DetalheProdutoDto build(
            Produto produto,
            Integer mediaNotas,
            Integer quantidadeNotas,
            List<OpiniaoDto> opinioes,
            List<PerguntaDto> perguntas
    ) {


        return new DetalheProdutoDto(
                produto.getLinks(),
                produto.getNome(),
                produto.getValor(),
                produto.getCaracteristicas(),
                produto.getDescricao(),
                mediaNotas,
                quantidadeNotas,
                opinioes,
                perguntas
        );
    }
}
