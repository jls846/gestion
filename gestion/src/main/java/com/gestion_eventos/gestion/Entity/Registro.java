package com.gestion_eventos.gestion.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del asistente es obligatorio")
    private String nombreAsistente;

    @Email(message = "El formato del correo electrónico es inválido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String correo;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    @ToString.Exclude 
    @JsonBackReference
    private Evento evento;

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreAsistente() { return nombreAsistente; }
    public void setNombreAsistente(String nombreAsistente) { this.nombreAsistente = nombreAsistente; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public void setEvento(Evento evento) { this.evento = evento; }
    public Evento getEvento() { return evento; }
}
