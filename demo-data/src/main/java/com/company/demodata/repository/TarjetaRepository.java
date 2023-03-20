package com.company.demodata.repository;

import com.company.demodata.model.Inversion;
import com.company.demodata.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
    void deleteAllByCliente_Id(int clienteId);
}
