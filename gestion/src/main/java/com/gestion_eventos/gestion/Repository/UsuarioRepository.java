package com.gestion_eventos.gestion.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_eventos.gestion.Entity.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByUsername(String username);

}
