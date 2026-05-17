package com.gestion_eventos.gestion.Controller;

import com.gestion_eventos.gestion.Entity.Evento;
import com.gestion_eventos.gestion.Entity.Categoria;
import com.gestion_eventos.gestion.Entity.Usuario;
import com.gestion_eventos.gestion.Repository.EventoRepository;
import com.gestion_eventos.gestion.Repository.CategoriaRepository;
import com.gestion_eventos.gestion.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // 1. Obtener TODOS los eventos del sistema (El que está llamando React ahora mismo)
    @GetMapping
    public ResponseEntity<List<Evento>> obtenerTodosLosEventos() {
        // CORRECCIÓN: Usamos el repositorio directo que ya tienes inyectado arriba
        List<Evento> eventos = eventoRepository.findAll(); 
        return ResponseEntity.ok(eventos);
    }

    // 2. Crear un nuevo evento asociando el Usuario y sus Categorías Many-to-Many
    @PostMapping("/usuario/{username}")
    public ResponseEntity<?> crearEvento(@RequestBody Evento evento, @PathVariable String username) {
        try {
            // Buscar al usuario creador en la base de datos por su username
            Usuario usuario = usuarioRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Error: Usuario '" + username + "' no encontrado."));
            evento.setCreador(usuario);

            // Procesar las categorías enviadas desde React
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

            // Guardar el evento (Inserta en tablas 'evento' y la intermedia 'evento_categoria')
            Evento eventoGuardado = eventoRepository.save(evento);
            return ResponseEntity.ok(eventoGuardado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }

    // 3. Obtener eventos de un usuario específico (Este lo tienes listo para cuando lo uses después)
    @GetMapping("/usuario/{username}")
    public ResponseEntity<List<Evento>> obtenerEventosPorUsuario(@PathVariable String username) {
        List<Evento> eventos = eventoRepository.findByCreadorUsername(username);
        return ResponseEntity.ok(eventos);
    }
}