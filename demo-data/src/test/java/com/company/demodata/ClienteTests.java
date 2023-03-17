package com.company.demodata;

import com.company.demodata.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class ClienteTests {

	@Test
	void newInstanceTest() {
		var cliente = new Cliente();
		cliente.setId(1);
		cliente.setNombre("John");
		cliente.setApellidos("Doe");
		cliente.setCedula("123456789");
		cliente.setTelefono("12345678");

		Assert.isTrue(cliente.getId() == 1, "Valida id");
		Assert.isTrue(cliente.getNombre() == "John", "Valida nombre");
		Assert.isTrue(cliente.getApellidos() == "Doe", "Valida apellidos");
		Assert.isTrue(cliente.getCedula() == "123456789", "Valida cedula");
		Assert.isTrue(cliente.getTelefono() == "12345678", "Valida telefono");
	}

}
