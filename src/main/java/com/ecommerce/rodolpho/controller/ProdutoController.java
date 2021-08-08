package com.ecommerce.rodolpho.controller;

import com.ecommerce.rodolpho.config.security.AutenticacaoService;
import com.ecommerce.rodolpho.controller.form.AddImagensForm;
import com.ecommerce.rodolpho.controller.form.NovoProdutoForm;
import com.ecommerce.rodolpho.model.Imagem;
import com.ecommerce.rodolpho.model.Produto;
import com.ecommerce.rodolpho.model.Usuario;
import com.ecommerce.rodolpho.repository.CategoriaRepository;
import com.ecommerce.rodolpho.repository.ProdutoRepository;
import com.ecommerce.rodolpho.shared.IdExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody NovoProdutoForm dto){

        Usuario usuarioLogado = AutenticacaoService.getUsuarioLogado();

        Produto produto = dto.toModel(categoriaRepository, usuarioLogado);
        produtoRepository.save(produto);

        return ResponseEntity.ok(produto);
    }

    @PostMapping(value = "{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionarImagens(
            @Valid AddImagensForm dto,
            @PathVariable("id") Long id
    ) {

        Usuario usuarioLogado = AutenticacaoService.getUsuarioLogado();

        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if(possivelProduto.isPresent()) {
            Produto produto = possivelProduto.get();
            Boolean isDono = produto.isDono(usuarioLogado);

            if(!isDono) return ResponseEntity.badRequest().build();
            List<String> links = dto.enviarImagens();

            produto = produto.adicionarImagens(links);
            produtoRepository.save(produto);

            return ResponseEntity.ok(produto);
        }

        return ResponseEntity.badRequest().build();
    }
}
