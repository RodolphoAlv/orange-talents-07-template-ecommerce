package com.ecommerce.rodolpho.controller;

import com.ecommerce.rodolpho.controller.form.NovaCategoriaForm;
import com.ecommerce.rodolpho.model.Categoria;
import com.ecommerce.rodolpho.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody NovaCategoriaForm dto) {

        Optional<Categoria> possivelCategoria = Optional.empty();

        if(dto.getCategoriaMaeId() != null)
            possivelCategoria = categoriaRepository.findById(dto.getCategoriaMaeId());

        Categoria categoria = dto.toModel(possivelCategoria);
        categoriaRepository.save(categoria);

        return ResponseEntity.ok(categoria);
    }
}
