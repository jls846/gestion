package com.gestion_eventos.gestion.Service;

import com.gestion_eventos.gestion.Entity.Evento;
import com.gestion_eventos.gestion.Entity.Inscripcion;
import com.gestion_eventos.gestion.Entity.Usuario;
import com.gestion_eventos.gestion.Repository.EventoRepository;
import com.gestion_eventos.gestion.Repository.InscripcionRepository;
import com.gestion_eventos.gestion.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Inscripcion registrarAsistente(Long eventoId, Long usuarioId) {
        //Buscar el evento y el usuario
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("El evento no existe"));
        
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        if (evento.getCreador().getId().equals(usuario.getId())) {
            throw new RuntimeException("No puedes inscribirte a tu propio evento.");
        }
        
        // Validar si ya está inscrito
        if (inscripcionRepository.existsByUsuarioIdAndEventoId(usuarioId, eventoId)) {
            throw new RuntimeException("Ya estás registrado en este evento");
        }

        //Validar capacidad máxima
        long inscritosActualmente = inscripcionRepository.countByEventoId(eventoId);
        if (inscritosActualmente >= evento.getCapacidadMaxima()) {
            throw new RuntimeException("Lo sentimos, el evento ya alcanzó su capacidad máxima.");
        }

        //Crear y guardar la inscripción
        Inscripcion nuevaInscripcion = new Inscripcion();
        nuevaInscripcion.setEvento(evento);
        nuevaInscripcion.setUsuario(usuario);

        return inscripcionRepository.save(nuevaInscripcion);
    }
}