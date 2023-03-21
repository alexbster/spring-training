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
class TarjetaServiceTests {

	@Autowired
	private TarjetaService tarjetaService;

	//Challenge ejercicio 3: actualizar estado de tarjeta
	@Test
	void actualizarEstadoUsandoId() {
		var tarjeta = tarjetaService.findTarjetaById(2);
		Assert.isTrue(!tarjeta.isEstado(), "Validacion estado previo");
		tarjetaService.actualizarEstadoUsandoId(2, true);
		tarjeta = tarjetaService.findTarjetaById(2);
		Assert.isTrue(tarjeta.isEstado(), "Validacion estado posterior");
	}
}
