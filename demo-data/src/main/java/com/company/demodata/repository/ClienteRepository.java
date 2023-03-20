package com.company.demodata.repository;

import com.company.demodata.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT t FROM Cliente t INNER JOIN Cuenta c ON t = c.cliente WHERE t.paisNacimiento = ?1 AND c.estado")
    public List<Cliente> getClientUsingCountryCodeWithActiveAccounts(String codigoPais);


    public List<Cliente> findClientesByPaisNacimientoAndCuentas_EstadoIsTrue(String paisNacimiento);

    @Query("SELECT c FROM Cliente c WHERE c.apellidos like %?1%")
    public List<Cliente> obtieneClientesPorApellido(String apellido);
}
