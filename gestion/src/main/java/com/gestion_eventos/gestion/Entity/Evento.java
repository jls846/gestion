package com.gestion_eventos.gestion.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;

    private String descripcion;
    private LocalDateTime fecha;
    private String lugar;

    @Min(value = 1)
    private Integer capacidadMaxima;

    private String organizador;

    //Relación con las Inscripciones
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "evento-inscripcion") 
    private List<Inscripcion> inscripciones = new ArrayList<>();
    //Relación con la categoria
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "evento_categoria", //Tabla intermedia
        joinColumns = @JoinColumn(name = "evento_id"),//llave del evento
        inverseJoinColumns = @JoinColumn(name = "categoria_id") //lave de la categoria
    )
    private List<Categoria> categorias = new ArrayList<>();
    //Relación con el Usuario que lo creo
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"eventosCreados", "inscripciones", "password"})
    private Usuario creador;

    // --- GETTERS Y SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }

    public Integer getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(Integer capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }

    public String getOrganizador() { return organizador; }
    public void setOrganizador(String organizador) { this.organizador = organizador; }

    public List<Inscripcion> getInscripciones() { return inscripciones; }
    public void setInscripciones(List<Inscripcion> inscripciones) { this.inscripciones = inscripciones; }

    public Usuario getCreador() { return creador; }
    public void setCreador(Usuario creador) { this.creador = creador; }

    public List<Categoria> getCategorias() { 
    return categorias; 
}

public void setCategorias(List<Categoria> categorias) { 
    this.categorias = categorias; 
}
}