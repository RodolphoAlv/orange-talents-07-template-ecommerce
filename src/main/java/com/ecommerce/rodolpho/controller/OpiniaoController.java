package com.ecommerce.rodolpho.controller;

import com.ecommerce.rodolpho.config.security.AutenticacaoService;
import com.ecommerce.rodolpho.controller.form.NovaOpiniaoForm;
import com.ecommerce.rodolpho.model.Opiniao;
import com.ecommerce.rodolpho.model.Produto;
import com.ecommerce.rodolpho.model.Usuario;
import com.ecommerce.rodolpho.repository.OpiniaoRepository;
import com.ecommerce.rodolpho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("produtos")
public class OpiniaoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @PostMapping("{id}/opinioes")
    public ResponseEntity<?> cadastrarOpiniao(
            @RequestBody @Valid NovaOpiniaoForm dto,
            @PathVariable("id") Long id
    ) {
        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if(possivelProduto.isPresent()) {
            Usuario usuarioLogado = AutenticacaoService.getUsuarioLogado();

            Produto produto = possivelProduto.get();
            Opiniao opiniao = dto.toModel(produto, usuarioLogado);
            opiniaoRepository.save(opiniao);

            return ResponseEntity.ok(opiniao);
        }

        return ResponseEntity.badRequest().build();
    }
}
