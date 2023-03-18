package com.company.demodata.service;

import com.company.demodata.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;
}
