package com.company.demodata;

import com.company.demodata.model.Cliente;
import com.company.demodata.model.Direccion;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class DireccionTests {

	@Test
	void newInstanceTest() {
		var cliente = new Cliente();
		cliente.setId(1);
		cliente.setNombre("John");
		cliente.setApellidos("Doe");
		cliente.setCedula("123456789");
		cliente.setTelefono("12345678");

		var direccion = new Direccion();
		direccion.setId(1);
		direccion.setDireccion("Costa Rica");
		direccion.setNomenclatura("CR");
		direccion.setCliente(cliente);

		Assert.isTrue(direccion.getId() == 1, "Valida id");
		Assert.isTrue(direccion.getDireccion() == "Costa Rica", "Valida direccion");
		Assert.isTrue(direccion.getNomenclatura() == "CR", "Valida nomenclatura");
		Assert.isTrue(direccion.getCliente().getId() == 1, "Valida id");
		Assert.isTrue(direccion.getCliente().getNombre() == "John", "Valida nombre");
		Assert.isTrue(direccion.getCliente().getApellidos() == "Doe", "Valida apellidos");
		Assert.isTrue(direccion.getCliente().getCedula() == "123456789", "Valida cedula");
		Assert.isTrue(direccion.getCliente().getTelefono() == "12345678", "Valida telefono");
		Assert.isTrue(cliente.getDirecciones().size() == 0, "Valida direcciones");
	}

}
