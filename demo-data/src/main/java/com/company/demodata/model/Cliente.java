package com.company.demodata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cliente")
@Setter
@Getter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private boolean estado;

    @Column(name = "pais_Nacimiento"
            , length = 2
            , columnDefinition = "varchar(2)")
    private String paisNacimiento;

    @OneToMany(mappedBy = "cliente"
            //, cascade = CascadeType.ALL
            , fetch = FetchType.LAZY
            , orphanRemoval = true)
    private List<Direccion> direcciones = new ArrayList<>();

    @OneToMany(mappedBy = "cliente"
            //, cascade = CascadeType.ALL
            , fetch = FetchType.LAZY
            , orphanRemoval = true)
    private List<Cuenta> cuentas = new ArrayList<>();

    @OneToMany(mappedBy = "cliente"
            //, cascade = CascadeType.ALL
            , fetch = FetchType.LAZY
            , orphanRemoval = true)
    private List<Tarjeta> tarjetas = new ArrayList<>();

    @OneToMany(mappedBy = "cliente"
            //, cascade = CascadeType.ALL
            , fetch = FetchType.LAZY
            , orphanRemoval = true)
    private List<Inversion> inversiones = new ArrayList<>();
}
