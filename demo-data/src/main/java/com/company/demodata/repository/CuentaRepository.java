package com.company.demodata.repository;

import com.company.demodata.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer>
        , JpaSpecificationExecutor<Cuenta> {

    void deleteAllByCliente_Id(int clienteId);

    List<Cuenta> findCuentaByCliente_IdAndEstadoIsTrue(int clienteId);

    List<Cuenta> findCuentaByCliente_IdAndEstadoIsTrueAndNumeroContains(int clienteId, String numero);

    Cuenta findCuentaByCliente_IdAndId(int clienteId, int id);

    List<Cuenta> findCuentaByCliente_Id(int clienteId);
}
