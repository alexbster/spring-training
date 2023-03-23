package com.company.demodata.repository;

import com.company.demodata.model.Cliente;
import com.company.demodata.model.Cuenta;
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
class CuentaRepositoryTests {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CuentaRepository cuentaRepository;

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

		var cuenta = new Cuenta();
		cuenta.setTipo("1");
		cuenta.setNumero("123");
		cuenta.setCliente(cliente);
		cuenta.setEstado(true);

		cuentaRepository.save(cuenta);

		var cuentaFromDb = cuentaRepository.findById(cuenta.getId());

		Assert.isTrue(cuentaFromDb.get().getId() == cuenta.getId(), "Valida id");
		Assert.isTrue(cuentaFromDb.get().getTipo().equals("1"), "Valida tipo");
		Assert.isTrue(cuentaFromDb.get().getNumero().equals("123"), "Valida numero");

	}

}
