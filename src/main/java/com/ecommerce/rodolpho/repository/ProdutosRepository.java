package com.ecommerce.rodolpho.repository;

import com.ecommerce.rodolpho.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {
}
