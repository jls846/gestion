package com.gestion_eventos.gestion.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_eventos.gestion.Entity.Registro;
import com.gestion_eventos.gestion.Service.RegistroService;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @PostMapping("/evento/{eventoId}")
    public ResponseEntity<?> registrar(@PathVariable Long eventoId, @RequestBody Registro registro) {
        try {
            Registro guardado = registroService.inscribirAsistente(eventoId, registro);
            return ResponseEntity.ok(guardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
