package com.company.demodata.model;

import com.company.demodata.model.Cliente;
import com.company.demodata.model.Tarjeta;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class TarjetaTests {

	@Test
	void newInstanceTest() {
		var cliente = new Cliente();
		cliente.setId(1);
		cliente.setNombre("John");
		cliente.setApellidos("Doe");
		cliente.setCedula("123456789");
		cliente.setTelefono("12345678");

		var tarjeta = new Tarjeta();
		tarjeta.setId(1);
		tarjeta.setTipo("1");
		tarjeta.setNumero("123");
		tarjeta.setCliente(cliente);

		Assert.isTrue(tarjeta.getId() == 1, "Valida id");
		Assert.isTrue(tarjeta.getTipo().equals("1"), "Valida tipo");
		Assert.isTrue(tarjeta.getNumero().equals("123"), "Valida numero");
		Assert.isTrue(tarjeta.getCliente().getId() == 1, "Valida id");
		Assert.isTrue(tarjeta.getCliente().getNombre().equals("John"), "Valida nombre");
		Assert.isTrue(tarjeta.getCliente().getApellidos().equals("Doe"), "Valida apellidos");
		Assert.isTrue(tarjeta.getCliente().getCedula().equals("123456789"), "Valida cedula");
		Assert.isTrue(tarjeta.getCliente().getTelefono().equals("12345678"), "Valida telefono");
		Assert.isTrue(cliente.getTarjetas().size() == 0, "Valida tarjetas");
	}

}
