package com.ecommerce.rodolpho.controller;

import com.ecommerce.rodolpho.controller.form.NovoUsuarioForm;
import com.ecommerce.rodolpho.model.Usuario;
import com.ecommerce.rodolpho.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody NovoUsuarioForm dto) {

        Usuario usuario = dto.toModel();
        usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
