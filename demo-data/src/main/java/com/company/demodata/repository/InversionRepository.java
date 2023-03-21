package com.company.demodata.repository;

import com.company.demodata.model.Cuenta;
import com.company.demodata.model.Direccion;
import com.company.demodata.model.Inversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InversionRepository extends JpaRepository<Inversion, Integer> {
    void deleteAllByCliente_Id(int clienteId);

    List<Inversion> findInversionByClienteIdAndEstadoIsTrue(int cliente);
}
