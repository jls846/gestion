package com.gestion_eventos.gestion.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestion_eventos.gestion.Entity.Evento;
import com.gestion_eventos.gestion.Service.EventoService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // Obtener todos los eventos: GET http://localhost:8080/api/eventos
    @GetMapping
    public List<Evento> listar() {
        return eventoService.listarTodos();
    }

    // Crear un evento: POST http://localhost:8080/api/eventos
    @PostMapping
    public ResponseEntity<Evento> crear(@RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.guardar(evento);
        return ResponseEntity.ok(nuevoEvento);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerPorId(@PathVariable Long id) {
        Evento evento = eventoService.buscarPorId(id);
        return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
    }
}