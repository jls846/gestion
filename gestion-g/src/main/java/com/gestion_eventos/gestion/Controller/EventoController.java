package com.gestion_eventos.gestion.Controller;

import com.gestion_eventos.gestion.Entity.Evento;
import com.gestion_eventos.gestion.Entity.Categoria;
import com.gestion_eventos.gestion.Entity.Usuario;
import com.gestion_eventos.gestion.Repository.EventoRepository;
import com.gestion_eventos.gestion.Repository.CategoriaRepository;
import com.gestion_eventos.gestion.Repository.UsuarioRepository;
import com.gestion_eventos.gestion.DTO.EventoImagenRequest;//NUEVO COMPONENTE,RESIVE EL ENDPOINT DE LAS IMAGENES
import com.gestion_eventos.gestion.Service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.ArrayList;
import java.util.List;

@Tag(name="Eventos",description="API para la gestion de eventos")
@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    //Cloudery
    @Autowired
    private EventoService eventoService;

    // Obtener TODOS los eventos del sistema
    @Operation(summary="Obtencion de todos nuestros eventos")
    @GetMapping
    public ResponseEntity<List<Evento>> obtenerTodosLosEventos() {
        List<Evento> eventos = eventoRepository.findAll(); 
        return ResponseEntity.ok(eventos);
    }

    // Crear un nuevo evento asociando el Usuario y sus Categorías Many-to-Many
    @Operation(summary="Creacion de un nuevo evento")
    @ApiResponses(value={
        @ApiResponse(responseCode="200",description="Evento creado correctamente"),
        @ApiResponse(responseCode="404",description="Usuario no encontrado"),
        @ApiResponse(responseCode="500",description="Error interno del servidor")
    })
    @PostMapping("/usuario/{username}")
    public ResponseEntity<?> crearEvento(@RequestBody Evento evento,@Parameter(description="Nombre del usuario creador del evento") @PathVariable String username) {
        try {
            Usuario usuario = usuarioRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Error: Usuario '" + username + "' no encontrado."));
            evento.setCreador(usuario);

            List<Categoria> categoriasCompletas = new ArrayList<>();
            if (evento.getCategorias() != null && !evento.getCategorias().isEmpty()) {
                for (Categoria cat : evento.getCategorias()) {
                    if (cat.getId() != null) {
                        categoriaRepository.findById(cat.getId())
                                .ifPresent(categoriasCompletas::add);
                    }
                }
            }

            evento.setCategorias(categoriasCompletas);

            Evento eventoGuardado = eventoRepository.save(evento);
            return ResponseEntity.ok(eventoGuardado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }

    // 3. Obtener eventos de un usuario específico
    @Operation(summary="Obtencion eventos de un usuario en especifico")
    @GetMapping("/usuario/{username}")
    public ResponseEntity<List<Evento>> obtenerEventosPorUsuario(@Parameter(description ="Username del usuario")@PathVariable String username) {
        List<Evento> eventos = eventoRepository.findByCreadorUsername(username);
        return ResponseEntity.ok(eventos);
    }

    // 4. NUEVO: Editar un evento existente por su ID
    @Operation(summary="Actualizacion un evento existente")
    @PutMapping("/{id}")
public ResponseEntity<?> actualizarEvento(@Parameter(description="Id del evento que actualizaremos")@PathVariable Long id, @RequestBody Evento eventoData) {
    return eventoRepository.findById(id)
        .map(eventoExistente -> {
            // Actualizamos únicamente las propiedades editables del formulario
            eventoExistente.setNombre(eventoData.getNombre());
            eventoExistente.setDescripcion(eventoData.getDescripcion());
            eventoExistente.setLugar(eventoData.getLugar());
            eventoExistente.setCapacidadMaxima(eventoData.getCapacidadMaxima());
            
            // Guardamos el objeto original de la BD modificado
            Evento guardado = eventoRepository.save(eventoExistente);
            return ResponseEntity.ok(guardado);
        })
        .orElse(ResponseEntity.notFound().build());
}

//Cloudery

@Operation(summary = "Actualizar imágenes de un evento")
@PutMapping("/{id}/imagenes")
public ResponseEntity<?> actualizarImagenes(
        @PathVariable Long id,
        @RequestBody EventoImagenRequest request) {
    try {
        eventoService.actualizarImagenes(
            id,
            request.getPortadaUrl(),
            request.getGaleriaUrls()
        );
        return ResponseEntity.ok("Imágenes guardadas correctamente");
    } catch (Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
}

}