package com.gestion_eventos.gestion.Service;

import org.springframework.stereotype.Service;
import com.gestion_eventos.gestion.Entity.*;
import com.gestion_eventos.gestion.Repository.*;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository; 
    private final CategoriaRepository categoriaRepository;
    
    public EventoService(EventoRepository eventoRepository, 
                         UsuarioRepository usuarioRepository, 
                         CategoriaRepository categoriaRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Evento registrarEventoCompleto(Evento evento, Long usuarioId) {
        // Buscamos y asignamos el creador
        Usuario creador = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));
        evento.setCreador(creador);

        // Sincronizamos las categorías enviadas en el JSON
        List<Long> categoriaIds = evento.getCategorias().stream()
                .map(Categoria::getId)
                .toList();
        
        List<Categoria> categoriasReales = categoriaRepository.findAllById(categoriaIds);
        evento.setCategorias(categoriasReales);

        // Guardamos todo (Se inserta en 'evento' y en 'evento_categoria')
        return eventoRepository.save(evento);
    }

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public void eliminar(Long id) {
        eventoRepository.deleteById(id);
    }
}