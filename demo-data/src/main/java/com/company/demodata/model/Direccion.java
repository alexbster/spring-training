package com.company.demodata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="direccion")
@Setter
@Getter
public class Direccion {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nombre"
            , length = 150
            , columnDefinition = "varchar(150)")
    private String direccion;

    @Column(name = "nomenclatura"
            , length = 100
            , columnDefinition = "varchar(100)")
    private String nomenclatura;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;
}
