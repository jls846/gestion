package com.gestion_eventos.gestion.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion_eventos.gestion.Entity.Evento;

import lombok.Lombok;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{

}
