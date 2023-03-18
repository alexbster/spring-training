package com.company.demodata.repository;

import com.company.demodata.model.Cliente;
import com.company.demodata.model.Cuenta;
import com.company.demodata.model.Direccion;
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
class DireccionRepositoryTests {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private DireccionRepository direccionRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	void add() {

		Assert.isTrue(direccionRepository.count() == 0, "Validacion de entitades existentes.");

		var cliente = new Cliente();
		cliente.setNombre("John");
		cliente.setApellidos("Doe");
		cliente.setCedula("123456789");
		cliente.setTelefono("12345678");

		clienteRepository.save(cliente);

		var direccion = new Direccion();
		direccion.setDireccion("SJ CR");
		direccion.setNomenclatura("40501");
		direccion.setCliente(cliente);

		direccionRepository.save(direccion);

		var direccionFromDb = direccionRepository.findById(direccion.getId());

		Assert.isTrue(direccionFromDb.get().getId() == direccion.getId(), "Valida id");
		Assert.isTrue(direccionFromDb.get().getNomenclatura().equals("40501"), "Valida nomenclatura");
		Assert.isTrue(direccionFromDb.get().getDireccion().equals("SJ CR"), "Valida direccion");

		Assert.isTrue(direccionRepository.count() == 1, "Validacion de entitades existentes.");
	}

}
