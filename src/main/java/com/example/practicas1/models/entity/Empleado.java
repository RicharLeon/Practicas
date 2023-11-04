package com.example.practicas1.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Empleados")
@Data
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_empleado")
    private Long idEmpleado;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "ID_tipo_documento")
    private char tipoDocumento;

    @Column(name = "Documento", unique = true)
    private String documento;

    @Column(name = "CodigoQR")
    private String codigoQR;

    @Column(name = "Dias_de_trabajo")
    private String diasDeTrabajo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_cargo")
    private Cargo cargo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_tipo_contrato")
    private TipoContrato tipoContrato;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_area")
    private Area area;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_proyecto")
    private Proyecto proyecto;

}
