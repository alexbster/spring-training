package com.company.demodata.service;

import com.company.demodata.repository.DireccionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DireccionService {
    private DireccionRepository direccionRepository;
}
