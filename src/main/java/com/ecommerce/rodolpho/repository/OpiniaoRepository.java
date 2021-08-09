package com.ecommerce.rodolpho.repository;

import com.ecommerce.rodolpho.model.Opiniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {
    @Query("select o from Opiniao o where o.produto.id = :id")
    List<Opiniao> findOpinioesByProdutoId(@Param("id") Long produtoId);
}
