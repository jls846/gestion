package com.gestion_eventos.gestion.Controller;

import com.gestion_eventos.gestion.Entity.Inscripcion;
import com.gestion_eventos.gestion.Service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "http://localhost:5173") // Para que React tenga permiso
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping("/evento/{eventoId}/usuario/{usuarioId}")
public ResponseEntity<?> inscribir(@PathVariable Long eventoId, @PathVariable Long usuarioId) {
    try {
        Inscripcion nueva = inscripcionService.registrarAsistente(eventoId, usuarioId);
        return ResponseEntity.ok(nueva);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}