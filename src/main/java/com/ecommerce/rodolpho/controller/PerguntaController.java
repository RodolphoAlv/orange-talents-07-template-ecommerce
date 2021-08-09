package com.ecommerce.rodolpho.controller;

import com.ecommerce.rodolpho.config.security.AutenticacaoService;
import com.ecommerce.rodolpho.controller.form.AddPerguntaForm;
import com.ecommerce.rodolpho.controller.form.NovaOpiniaoForm;
import com.ecommerce.rodolpho.model.Opiniao;
import com.ecommerce.rodolpho.model.Pergunta;
import com.ecommerce.rodolpho.model.Produto;
import com.ecommerce.rodolpho.model.Usuario;
import com.ecommerce.rodolpho.repository.OpiniaoRepository;
import com.ecommerce.rodolpho.repository.PerguntaRepository;
import com.ecommerce.rodolpho.repository.ProdutoRepository;
import com.ecommerce.rodolpho.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("produtos")
public class PerguntaController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PerguntaRepository perguntaRepository;

    @PostMapping("{id}/perguntas")
    public ResponseEntity<?> cadastrarOpiniao(
            @RequestBody @Valid AddPerguntaForm dto,
            @PathVariable("id") Long id
    ) {
        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if(possivelProduto.isPresent()) {
            Usuario usuarioLogado = AutenticacaoService.getUsuarioLogado();

            Produto produto = possivelProduto.get();
            Pergunta pergunta = dto.toModel(usuarioLogado, produto);
            perguntaRepository.save(pergunta);

            EmailSender.enviarEmail(
                    usuarioLogado.getUsername(),
                    produto.getEmailDono(),
                    "Nova pergunta cadastrada no produto de id #" + id
            );

            return ResponseEntity.ok(pergunta);
        }

        return ResponseEntity.badRequest().build();
    }
}
