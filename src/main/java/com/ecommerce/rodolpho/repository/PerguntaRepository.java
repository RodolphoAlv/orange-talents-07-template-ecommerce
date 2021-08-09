package com.ecommerce.rodolpho.repository;

import com.ecommerce.rodolpho.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
    @Query("select p from Pergunta p where p.produto.id = :id")
    List<Pergunta> findPerguntasByProdutoId(@Param("id") Long id);
}
