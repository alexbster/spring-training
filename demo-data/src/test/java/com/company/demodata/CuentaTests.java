package com.company.demodata;

import com.company.demodata.model.Cliente;
import com.company.demodata.model.Cuenta;
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
		Assert.isTrue(cuenta.getTipo() == "1", "Valida tipo");
		Assert.isTrue(cuenta.getNumero() == "123", "Valida numero");
		Assert.isTrue(cuenta.getCliente().getId() == 1, "Valida id");
		Assert.isTrue(cuenta.getCliente().getNombre() == "John", "Valida nombre");
		Assert.isTrue(cuenta.getCliente().getApellidos() == "Doe", "Valida apellidos");
		Assert.isTrue(cuenta.getCliente().getCedula() == "123456789", "Valida cedula");
		Assert.isTrue(cuenta.getCliente().getTelefono() == "12345678", "Valida telefono");
		Assert.isTrue(cliente.getCuentas().size() == 0, "Valida cuentas");
	}

}
