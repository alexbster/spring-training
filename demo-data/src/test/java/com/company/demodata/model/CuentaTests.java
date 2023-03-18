package com.company.demodata.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CuentaTests {

	@Test
	void newInstanceTest() {
		var cliente = new Cliente();
		cliente.setId(1);
		cliente.setNombre("John");
		cliente.setApellidos("Doe");
		cliente.setCedula("123456789");
		cliente.setTelefono("12345678");

		var cuenta = new Cuenta();
		cuenta.setId(1);
		cuenta.setTipo("1");
		cuenta.setNumero("123");
		cuenta.setCliente(cliente);

		Assert.isTrue(cuenta.getId() == 1, "Valida id");
		Assert.isTrue(cuenta.getTipo().equals("1"), "Valida tipo");
		Assert.isTrue(cuenta.getNumero().equals("123"), "Valida numero");
		Assert.isTrue(cuenta.getCliente().getId() == 1, "Valida id");
		Assert.isTrue(cuenta.getCliente().getNombre().equals("John"), "Valida nombre");
		Assert.isTrue(cuenta.getCliente().getApellidos().equals("Doe"), "Valida apellidos");
		Assert.isTrue(cuenta.getCliente().getCedula().equals("123456789"), "Valida cedula");
		Assert.isTrue(cuenta.getCliente().getTelefono().equals("12345678"), "Valida telefono");
		Assert.isTrue(cliente.getCuentas().size() == 0, "Valida cuentas");
	}

}
