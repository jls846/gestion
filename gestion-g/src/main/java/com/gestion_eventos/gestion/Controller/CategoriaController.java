package com.gestion_eventos.gestion.Controller;//CONTROLADOR CREADO PARA EXPONER LAS CATEGORIAS DESDE LA BD AL FRONT

import com.gestion_eventos.gestion.Entity.Categoria;
import com.gestion_eventos.gestion.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerTodas() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

}
