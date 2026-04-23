package com.gestion_eventos.gestion.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion_eventos.gestion.Entity.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>{

}
