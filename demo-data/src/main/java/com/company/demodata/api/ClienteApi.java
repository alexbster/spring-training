package com.company.demodata.api;

import com.company.demodata.dto.ClienteDto;
import com.ctc.wstx.shaded.msv_core.util.Uri;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.company.demodata.service.ClienteService;

import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/v1/api/clientes")
@Slf4j
public class ClienteApi {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ClienteDto buscarClientePorId(@PathVariable int id) {
        return clienteService.getClient(id);
    }

    @GetMapping("")
    public List<ClienteDto> buscarClientes() {
        return clienteService.obtieneTodosLosClientes();
    }

    @GetMapping(value = "/{id}/xml", produces = "application/xml")
    public ClienteDto buscarClientePorIdEnXml(@PathVariable int id) {
        return clienteService.getClient(id);
    }

    @GetMapping(value = "/{id}/accept", produces = {"application/xml", "application/json"})
    public ClienteDto buscarClientePorIdEnXmlJson(@PathVariable int id) {
        return clienteService.getClient(id);
    }

    @GetMapping(value = "/parameters", produces = {"application/xml", "application/json"})
    public ClienteDto buscarClientePorIdEnQueryParam(@RequestParam int id) {
        return clienteService.getClient(id);
    }

    @PostMapping(produces = {"application/xml", "application/json"})
    public ResponseEntity<ClienteDto> crearCliente(@RequestBody ClienteDto clienteDto) {
        clienteService.insertClient(clienteDto);
        return new ResponseEntity(clienteDto, org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping(produces = {"application/xml", "application/json"})
    public ResponseEntity<ClienteDto> actualizaCliente(@RequestBody ClienteDto clienteDto) {
        clienteService.updateClient(clienteDto);
        return new ResponseEntity(clienteDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void eliminarClientePorId(@PathVariable int id) {
        clienteService.deleteClient(id);
    }
}
