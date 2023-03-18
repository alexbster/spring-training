package com.company.demodata.repository;

import com.company.demodata.model.Cliente;
import com.company.demodata.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
}
