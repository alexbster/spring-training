package com.company.demodata.repository;

import com.company.demodata.model.Direccion;
import com.company.demodata.model.Inversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InversionRepository extends JpaRepository<Inversion, Integer> {
}
