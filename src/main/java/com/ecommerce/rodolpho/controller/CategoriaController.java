package com.ecommerce.rodolpho.controller;

import com.ecommerce.rodolpho.controller.form.NovaCategoriaForm;
import com.ecommerce.rodolpho.model.Categoria;
import com.ecommerce.rodolpho.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody NovaCategoriaForm dto) {

        Categoria categoria = dto.toModel(categoriaRepository);
        categoriaRepository.save(categoria);

        return ResponseEntity.ok(categoria);
    }
}
