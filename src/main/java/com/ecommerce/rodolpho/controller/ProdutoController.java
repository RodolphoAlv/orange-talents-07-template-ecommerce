package com.ecommerce.rodolpho.controller;

import com.ecommerce.rodolpho.controller.form.NovoProdutoForm;
import com.ecommerce.rodolpho.model.Produto;
import com.ecommerce.rodolpho.repository.CategoriaRepository;
import com.ecommerce.rodolpho.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutosRepository produtosRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody NovoProdutoForm dto){

        Produto produto = dto.toModel(categoriaRepository);
        produtosRepository.save(produto);

        return ResponseEntity.ok(produto);
    }
}
