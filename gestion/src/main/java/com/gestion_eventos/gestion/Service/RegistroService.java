package com.gestion_eventos.gestion.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_eventos.gestion.Entity.Evento;
import com.gestion_eventos.gestion.Entity.Registro;
import com.gestion_eventos.gestion.Repository.EventoRepository;
import com.gestion_eventos.gestion.Repository.RegistroRepository;

import jakarta.transaction.Transactional;

@Service
public class RegistroService {
    
    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Transactional
    public Registro inscribirAsistente(Long eventoId, Registro nuevoRegistro){

        Evento evento = eventoRepository.findById(eventoId)
            .orElseThrow(() -> new RuntimeException("Evento no existe"));

        if (evento.getRegistros().size() >= evento.getCapacidadMaxima()){
            throw new RuntimeException("Lo sentimos, el evento ha alcanzado su capacidad maxima");
        }

        nuevoRegistro.setEvento(evento);

        return registroRepository.save(nuevoRegistro);
    }

}
