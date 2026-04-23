package com.gestion_eventos.gestion.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;
    private LocalDateTime fecha;
    private String lugar;

    @Min (value = 1, message = "La capacidad debe ser al menos 1")
    private Integer capacidadMaxima;

    private String organizador;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Registro> registros = new ArrayList<>();

    // Sustituye @Data por esto si Lombok sigue fallando
public String getNombre() { return nombre; }
public Integer getCapacidadMaxima() { return capacidadMaxima; }
public List<Registro> getRegistros() { return registros; }

public void setNombre(String nombre) { this.nombre = nombre; }
public void setCapacidadMaxima(Integer capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }
public void setRegistros(List<Registro> registros) { this.registros = registros; }
}
