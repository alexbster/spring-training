package com.company.demodata.repository;

import com.company.demodata.model.Cliente;
import com.company.demodata.model.Cuenta;
import com.company.demodata.model.Inversion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@Slf4j
class InversionRepositoryTests {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private InversionRepository inversionRepository;

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

		var inversion = new Inversion();
		inversion.setTipo("1");
		inversion.setNumero("123");
		inversion.setCliente(cliente);

		inversionRepository.save(inversion);

		var inversionFromDb = inversionRepository.findById(inversion.getId());

		Assert.isTrue(inversionFromDb.get().getId() == inversion.getId(), "Valida id");
		Assert.isTrue(inversionFromDb.get().getTipo().equals("1"), "Valida tipo");
		Assert.isTrue(inversionFromDb.get().getNumero().equals("123"), "Valida numero");

	}

}
