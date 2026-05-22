package com.gestion_eventos.gestion.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_eventos.gestion.Entity.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long>{

    boolean existsByUsuarioIdAndEventoId(Long usuarioId, Long eventoId);

    long countByEventoId(Long eventoId);
}
