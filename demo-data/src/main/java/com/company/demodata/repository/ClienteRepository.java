package com.company.demodata.repository;

import com.company.demodata.model.Cliente;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT t FROM Cliente t INNER JOIN Cuenta c ON t = c.cliente WHERE t.paisNacimiento = ?1 AND c.estado")
    public List<Cliente> getClientUsingCountryCodeWithActiveAccounts(String codigoPais);


    //Derived methods
    public List<Cliente> findClientesByPaisNacimientoAndCuentas_EstadoIsTrue(String paisNacimiento);

    @Query("SELECT c FROM Cliente c WHERE c.apellidos like %:apellido%")
    public List<Cliente> obtieneClientesPorApellidoQueryLanguage(String apellido);

    @Query(value = "SELECT nombre,apellidos,cedula,telefono,id FROM Cliente WHERE apellidos like %:apellido%"
    , nativeQuery = true)
    public List<Tuple> obtieneClientesPorApellidoQueryLanguageNativeQuery(String apellido);


    @Query("SELECT c FROM Cliente c INNER JOIN Tarjeta t ON c = t.cliente WHERE c.paisNacimiento != :codigoPaisLocal AND t.estado = false")
    public List<Cliente> obtieneClientesExtrajerosConTarjetasInactivas(String codigoPaisLocal);
}
