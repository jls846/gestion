package com.gestion_eventos.gestion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion_eventos.gestion.Entity.Evento;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
    List<Evento> findByCreadorUsername(String username);
}
