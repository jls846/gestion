package com.gestion_eventos.gestion.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestion_eventos.gestion.Entity.Evento;
import com.gestion_eventos.gestion.Entity.Usuario;
import com.gestion_eventos.gestion.Repository.UsuarioRepository;
import com.gestion_eventos.gestion.Repository.EventoRepository; // Importación limpia
import com.gestion_eventos.gestion.Service.EventoService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EventoRepository eventoRepository;

    // 1. Obtener eventos SOLO del usuario logueado
    @GetMapping("/usuario/{username}")
    public List<Evento> obtenerEventosPorUsuario(@PathVariable String username) {
        return eventoRepository.findByCreadorUsername(username);
    }

    // 2. Crear evento asignándole el dueño
@PostMapping("/usuario/{username}")
public ResponseEntity<?> crearEvento(@PathVariable String username, @RequestBody Evento evento) {
    // 1. Buscamos al usuario
    java.util.Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

    // 2. Validamos si existe
    if (usuarioOpt.isEmpty()) {
        return ResponseEntity.badRequest().body("Error: El usuario '" + username + "' no existe.");
    }

    // 3. Si existe, procesamos
    Usuario usuario = usuarioOpt.get();
    evento.setCreador(usuario);
    
    try {
        Evento nuevoEvento = eventoRepository.save(evento);
        return ResponseEntity.ok(nuevoEvento);
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body("Error al guardar el evento: " + e.getMessage());
    }
}
    // 3. Este método ahora es "Global" (puedes usarlo para que los invitados vean todos los eventos)
    @GetMapping
    public List<Evento> listar() {
        return eventoService.listarTodos();
    }
}