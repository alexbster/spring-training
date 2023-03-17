package com.company.demodata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cliente")
@Setter
@Getter
public class Cliente {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nombre"
            , length = 50
            , columnDefinition = "varchar(50)")
    private String nombre;

    @Column(name = "apellidos"
            , length = 50
            , columnDefinition = "varchar(50)")
    private String apellidos;

    @Column(name = "cedula"
            , length = 15
            , columnDefinition = "varchar(15)")
    private String cedula;

    @Column(name = "telefono"
            , length = 11
            , columnDefinition = "varchar(11)")
    private String telefono;
}
