package com.gestion_eventos.gestion.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_eventos.gestion.Entity.Evento;
import com.gestion_eventos.gestion.Repository.EventoRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

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
