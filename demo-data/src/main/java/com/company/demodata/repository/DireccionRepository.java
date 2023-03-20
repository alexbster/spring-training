package com.company.demodata.repository;

import com.company.demodata.model.Cuenta;
import com.company.demodata.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
    void deleteAllByCliente_Id(int clienteId);
}
