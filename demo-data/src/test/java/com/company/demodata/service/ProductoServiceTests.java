package com.company.demodata.service;

import com.company.demodata.dto.CuentaDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@Slf4j
//@Sql("classpath:datainicial.sql")
class ProductoServiceTests {

	@Autowired
	private ProductoService productoService;

	//Challenge ejercicio 2: consulta de productos activos de un cliente
	@Test
	void obtieneProductosActivosPorCliente() {
		var productosDto = productoService.obtieneProductosActivosPorCliente(1);
		Assert.isTrue(productosDto.getInversiones().size() == 0, "Validacion de inversiones");
		Assert.isTrue(productosDto.getCuentas().size() == 2, "Validacion de inversiones");
		Assert.isTrue(productosDto.getTarjetas().size() == 1, "Validacion de tarjetas");
	}
}
