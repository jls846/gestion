package com.gestion_eventos.gestion.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_eventos.gestion.Entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
