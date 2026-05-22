package com.gestion_eventos.gestion.Controller;

import com.gestion_eventos.gestion.Entity.Inscripcion;
import com.gestion_eventos.gestion.Service.InscripcionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Inscripciones",
    description = "API para la gestión de inscripciones a eventos"
)

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "http://localhost:5173") // Para que React tenga permiso
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Operation(
        summary = "Registrar inscripción a un evento",
        description = "Permite registrar un usuario como asistente de un evento"
    )

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Inscripción realizada correctamente"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Error al realizar la inscripción"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Evento o usuario no encontrado"
        )
    })

    @PostMapping("/evento/{eventoId}/usuario/{usuarioId}")
    public ResponseEntity<?> inscribir(

            @Parameter(description = "ID del evento")
            @PathVariable Long eventoId,

            @Parameter(description = "ID del usuario")
            @PathVariable Long usuarioId) {

        try {

            Inscripcion nueva =
                    inscripcionService.registrarAsistente(eventoId, usuarioId);

            return ResponseEntity.ok(nueva);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}