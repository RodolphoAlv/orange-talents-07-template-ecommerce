package com.ecommerce.rodolpho.repository;

import com.ecommerce.rodolpho.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
