package com.gestion_eventos.gestion.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categoria")
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;

    @ManyToMany(mappedBy = "categorias")//nombre del atribo de Evento
    @JsonIgnore
    private List<Evento> eventos = new ArrayList<>();

    // --- GETTERS Y SETTERS

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getCategoria() {
    return categoria;
}

public void setCategoria(String categoria) {
    this.categoria = categoria;
}

public List<Evento> getEventos() {
    return eventos;
}

public void setEventos(List<Evento> eventos) {
    this.eventos = eventos;
}
}
