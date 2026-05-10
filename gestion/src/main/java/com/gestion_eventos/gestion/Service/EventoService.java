package com.gestion_eventos.gestion.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_eventos.gestion.Entity.Evento;
import com.gestion_eventos.gestion.Entity.Usuario;
import com.gestion_eventos.gestion.Repository.EventoRepository;
import com.gestion_eventos.gestion.Repository.UsuarioRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired 
    private UsuarioRepository usuarioRepository; 

public Evento guardarConUsuario(Evento evento, Long usuarioId) {
    Usuario creador = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    evento.setCreador(creador); 
    return eventoRepository.save(evento);
}
    // Obtener todos los eventos
    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    // Guardar un nuevo evento
    public Evento guardar(Evento evento) {
        return eventoRepository.save(evento);
    }

    // Buscar por ID
    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    // Eliminar
    public void eliminar(Long id) {
        eventoRepository.deleteById(id);
}
}
