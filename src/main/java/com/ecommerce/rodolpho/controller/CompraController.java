package com.ecommerce.rodolpho.controller;

import com.ecommerce.rodolpho.config.security.AutenticacaoService;
import com.ecommerce.rodolpho.controller.form.CompraForm;
import com.ecommerce.rodolpho.model.Compra;
import com.ecommerce.rodolpho.model.Produto;
import com.ecommerce.rodolpho.model.Usuario;
import com.ecommerce.rodolpho.repository.CompraRepository;
import com.ecommerce.rodolpho.repository.ProdutoRepository;
import com.ecommerce.rodolpho.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("compras")
public class CompraController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> fazerCompra(@Valid @RequestBody CompraForm dto) {

        Produto produto = produtoRepository.getById(dto.getProdutoId());
        Boolean ajusteEstoque = produto.ajustarQuantidade(dto.getQuantidade());

        System.out.println(ajusteEstoque);

        if(ajusteEstoque) {
            Usuario usuario = AutenticacaoService.getUsuarioLogado();
            Compra compra = dto.toModel(produto, usuario);

            compraRepository.save(compra);
            produtoRepository.save(produto);

            EmailSender.enviarEmail(
                    usuario.getUsername(),
                    produto.getEmailDono(),
                    "A compra de id #" + compra.getId() + " foi iniciada");

            String urlPagamento = dto.getGateway()
                    .urlPagamento(compra.getId(), "localhost:8080");

            Map<String, String> headers = Map.of("Location", urlPagamento);
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }

        return ResponseEntity.badRequest().build();
    }
}
