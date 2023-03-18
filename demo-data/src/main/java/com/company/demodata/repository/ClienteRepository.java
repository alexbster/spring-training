package com.company.demodata.repository;

import com.company.demodata.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT t FROM Cliente t INNER JOIN Cuenta c ON t = c.cliente WHERE t.codigoPais = ?1 AND c.activa")
    public List<Cliente> getClientUsingCountryCodeWithActiveAccounts(String codigoPais);
}
