package com.company.demodata.repository;

import com.company.demodata.model.Cliente;
import com.company.demodata.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class ClienteRepositoryTests {

	@Autowired
	private ClienteRepository clienteRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	void add() {


		var cliente = new Cliente();
		cliente.setNombre("John");
		cliente.setApellidos("Doe");
		cliente.setCedula("123456789");
		cliente.setTelefono("12345678");

		clienteRepository.save(cliente);

		var clienteFromDb = clienteRepository.findById(cliente.getId());

		System.out.println(clienteFromDb.get().getNombre());

		Assert.isTrue(clienteFromDb.isPresent(), "Registro no encontrado");
		Assert.isTrue(clienteFromDb.get().getNombre().equals("John"), "Valida nombre");
		Assert.isTrue(clienteFromDb.get().getApellidos().equals("Doe"), "Valida apellidos");
		Assert.isTrue(clienteFromDb.get().getCedula().equals("123456789"), "Valida cedula");
		Assert.isTrue(clienteFromDb.get().getTelefono().equals("12345678"), "Valida telefono");

	}

}
