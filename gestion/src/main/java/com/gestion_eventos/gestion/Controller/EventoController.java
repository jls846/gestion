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
    // 1. Buscamos al usuario de forma segura
    java.util.Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
    
    // 2. Si no existe, respondemos de inmediato con error
    if (usuarioOpt.isEmpty()) {
        return ResponseEntity.badRequest().body("Error: El usuario '" + username + "' no existe.");
    }

    // 3. Si existe, lo extraemos y lo asignamos como creador
    Usuario usuario = usuarioOpt.get();
    evento.setCreador(usuario);
    
    // 4. Guardamos y devolvemos el evento creado
    Evento nuevoEvento = eventoRepository.save(evento);
    return ResponseEntity.ok(nuevoEvento);
}

    // 3. Este método ahora es "Global" (puedes usarlo para que los invitados vean todos los eventos)
    @GetMapping
    public List<Evento> listar() {
        return eventoService.listarTodos();
    }
}