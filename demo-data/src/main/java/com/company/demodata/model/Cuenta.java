package com.company.demodata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cuenta")
@Setter
@Getter
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "numero"
            , length = 50
            , columnDefinition = "varchar(50)")
    @NotNull(message = "El numero no puede ser nulo") //Code challenge
    @NotBlank(message = "El numero no puede ser nulo") //Code challenge
    @Pattern(regexp = "\\d+", message = "El tipo debe ser Ahorro o Corriente") //Code challenge
    private String numero;

    @Column(name = "tipo"
            , length = 10
            , columnDefinition = "varchar(10)")
    @NotNull(message = "El tipo no puede ser nulo") //Code challenge
    @Pattern(regexp = ".+", message = "El tipo debe ser Ahorro o Corriente") //Code challenge
    private String tipo;

    //@AssertTrue(message = "El estado no puede ser nulo")//Code challenge
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

}
