package com.example.practicas1.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Proyectos")
@Data
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_proyecto")
    public int idProyecto;
    @Column(name = "Nombre")
    public String Nombre;
    @Column(name = "Fecha_inicio")
    public LocalDate fechaInicio;
    @Column(name = "Fecha_fin")
    public LocalDate fechaFin;
    @Column(name = "Estado")
    public String estado;


}
