package com.company.demodata.api;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.CuentaDto;
import com.company.demodata.service.CuentaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cuentas")
@Slf4j
public class CuentaApi {

    @Autowired
    private CuentaService cuentaService;


    @GetMapping(value = "/cliente/{id}", produces = {"application/xml", "application/json"})
    public ResponseEntity<List<ClienteDto>> obtieneCuentaPorCliente(@PathVariable int id) {
        var cuentas = cuentaService.obtieneCuentasPorCliente(id);
        if(cuentas == null || cuentas.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(cuentas, HttpStatus.OK);
    }
    @PostMapping(produces = {"application/xml", "application/json"})
    public ResponseEntity<ClienteDto> crearCuentaPorCliente(@Valid @RequestBody CuentaDto cuentaDto) {
        cuentaService.insertCuenta(cuentaDto);
        return new ResponseEntity(cuentaDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cuentaId}/cliente/{clienteId}/disable", produces = {"application/xml", "application/json"})
    public ResponseEntity disableCuentaPorCliente(@PathVariable int clienteId, @PathVariable int cuentaId) {
        var cuentaDto = cuentaService.disableCuenta(clienteId, cuentaId);
        if(cuentaDto == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(cuentaDto, HttpStatus.OK);
    }
}
