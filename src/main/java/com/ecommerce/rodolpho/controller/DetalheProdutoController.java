package com.ecommerce.rodolpho.controller;

import com.ecommerce.rodolpho.controller.dto.DetalheProdutoDto;
import com.ecommerce.rodolpho.controller.dto.OpiniaoDto;
import com.ecommerce.rodolpho.controller.dto.PerguntaDto;
import com.ecommerce.rodolpho.model.Opiniao;
import com.ecommerce.rodolpho.model.Pergunta;
import com.ecommerce.rodolpho.model.Produto;
import com.ecommerce.rodolpho.repository.OpiniaoRepository;
import com.ecommerce.rodolpho.repository.PerguntaRepository;
import com.ecommerce.rodolpho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("produtos")
public class DetalheProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;



    @GetMapping("{id}/detalhes")
    @Transactional
    public ResponseEntity<?> buscarDetalhesDoProduto(@PathVariable("id") Long id) {

        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if(possivelProduto.isPresent()) {
            Produto produto = possivelProduto.get();

            List<OpiniaoDto> opinioesDoProduto = opiniaoRepository
                    .findOpinioesByProdutoId(id).stream()
                    .map(Opiniao::toResponse)
                    .collect(Collectors.toList());

            Integer quantidadeOpinioes = opinioesDoProduto.size();

            Integer soma = opinioesDoProduto.stream()
                    .map(OpiniaoDto::getNota)
                    .reduce(0, Integer::sum);

            Integer mediaNotas = null;
            if(quantidadeOpinioes != 0) {
                mediaNotas = soma/quantidadeOpinioes;
            }

            List<PerguntaDto> perguntas = perguntaRepository
                    .findPerguntasByProdutoId(id).stream()
                    .map(Pergunta::toResponse)
                    .collect(Collectors.toList());

            DetalheProdutoDto detalheProduto = DetalheProdutoDto.build(
                    produto, mediaNotas, quantidadeOpinioes, opinioesDoProduto, perguntas
            );

            return ResponseEntity.ok(detalheProduto);
        }

        return ResponseEntity.badRequest().build();
    }
}
