package com.gestion_eventos.gestion.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gestion_eventos.gestion.Entity.Categoria;
import com.gestion_eventos.gestion.Repository.CategoriaRepository;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository){
        this.repository = repository;
    }

    //bucamos por IDs
    public List<Categoria> buscarPorIds(List<Long> ids){
        return repository.findAllById(ids);
    }
}
