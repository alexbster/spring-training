package com.company.demodata.repository;

import com.company.demodata.model.Cliente;
import com.company.demodata.model.Inversion;
import com.company.demodata.model.Tarjeta;
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
class TarjetaRepositoryTests {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TarjetaRepository tarjetaRepository;

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

		var tarjeta = new Tarjeta();
		tarjeta.setTipo("1");
		tarjeta.setNumero("123");
		tarjeta.setCliente(cliente);

		tarjetaRepository.save(tarjeta);

		var tarjetaFromDb = tarjetaRepository.findById(tarjeta.getId());

		Assert.isTrue(tarjetaFromDb.get().getId() == tarjeta.getId(), "Valida id");
		Assert.isTrue(tarjetaFromDb.get().getTipo().equals("1"), "Valida tipo");
		Assert.isTrue(tarjetaFromDb.get().getNumero().equals("123"), "Valida numero");

	}

}
