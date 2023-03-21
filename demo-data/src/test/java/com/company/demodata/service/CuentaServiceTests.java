package com.company.demodata.service;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.dto.CuentaDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@Slf4j
//@Sql("classpath:datainicial.sql")
class CuentaServiceTests {

	@Autowired
	private CuentaService cuentaService;

	//Challenge ejercicio 1: consulta de cuentas de un cliente
	@Test
	void buscarCuentaPorCriterio() {

		var cuentaDto = new CuentaDto();
		cuentaDto.setEstado(true);
		cuentaDto.setClienteId(1);
		cuentaDto.setEstado(true);
		var clients = cuentaService.buscarCuentaPorCriterioUsandoClienteId(cuentaDto);
		Assert.isTrue(clients.size() == 2, "Validacion de existecias");
	}
}
