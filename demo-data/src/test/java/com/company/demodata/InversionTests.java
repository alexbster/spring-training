package com.company.demodata;

import com.company.demodata.model.Cliente;
import com.company.demodata.model.Inversion;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class InversionTests {

	@Test
	void newInstanceTest() {
		var cliente = new Cliente();
		cliente.setId(1);
		cliente.setNombre("John");
		cliente.setApellidos("Doe");
		cliente.setCedula("123456789");
		cliente.setTelefono("12345678");

		var inversion = new Inversion();
		inversion.setId(1);
		inversion.setTipo("1");
		inversion.setNumero("123");
		inversion.setCliente(cliente);

		Assert.isTrue(inversion.getId() == 1, "Valida id");
		Assert.isTrue(inversion.getTipo() == "1", "Valida tipo");
		Assert.isTrue(inversion.getNumero() == "123", "Valida numero");
		Assert.isTrue(inversion.getCliente().getId() == 1, "Valida id");
		Assert.isTrue(inversion.getCliente().getNombre() == "John", "Valida nombre");
		Assert.isTrue(inversion.getCliente().getApellidos() == "Doe", "Valida apellidos");
		Assert.isTrue(inversion.getCliente().getCedula() == "123456789", "Valida cedula");
		Assert.isTrue(inversion.getCliente().getTelefono() == "12345678", "Valida telefono");
	}

}
