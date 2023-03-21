package com.company.demodata.service;

import com.company.demodata.dto.ClienteDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@Slf4j
//@Sql("classpath:datainicial.sql")
class ClienteServiceTests {

	@Autowired
	private ClienteService clienteService;

	@Test
	@Order(1)
	void insertClient() {
		var clienteDto = new ClienteDto();
		clienteDto.setNombre("John");
		clienteDto.setApellidos("Doe");
		clienteDto.setCedula("123456789");
		clienteDto.setTelefono("12345678");

		clienteService.insertClient(clienteDto);

		Assert.isTrue(clienteDto.getId() != 0, "Registro no encontrado");
	}


	@Test
	@Order(2)
	void getClient() {
		var clienteDto = new ClienteDto();
		clienteDto.setNombre("John");
		clienteDto.setApellidos("Doe");
		clienteDto.setCedula("123456789");
		clienteDto.setTelefono("12345678");

		clienteService.insertClient(clienteDto);

		var generatedClient = clienteService.getClient(clienteDto.getId());

		Assert.isTrue(generatedClient.getId() == clienteDto.getId(), "Validacion de id");
		Assert.isTrue(generatedClient.getApellidos().equals("Doe"), "Validacion de apellidos");
		Assert.isTrue(generatedClient.getNombre().equals("John"), "Validacion de nombre");
		Assert.isTrue(generatedClient.getCedula().equals("123456789"), "Validacion de cedula");
		Assert.isTrue(generatedClient.getTelefono().equals("12345678"), "Validacion de telefono");
	}

	@Test
	@Order(3)
	void getClientNotFoundException() {
		try
		{
			var generatedClient = clienteService.getClient(123456789);
			Assert.isNull(generatedClient, "Encontró un registro");
		}
		catch (RuntimeException ex)
		{
			Assert.isTrue(ex.getMessage().equals("Cliente no existe"), "Validacion de not found");
		}
	}

	@Test
	@Order(4)
	void updateClient() {
		var clienteDto = new ClienteDto();
		clienteDto.setNombre("John");
		clienteDto.setApellidos("Doe");
		clienteDto.setCedula("123456789");
		clienteDto.setTelefono("12345678");

		clienteService.insertClient(clienteDto);

		var generatedClient = clienteService.getClient(clienteDto.getId());

		Assert.isTrue(generatedClient.getId() == clienteDto.getId(), "Validacion de id");
		Assert.isTrue(generatedClient.getApellidos().equals("Doe"), "Validacion de apellidos");
		Assert.isTrue(generatedClient.getNombre().equals("John"), "Validacion de nombre");
		Assert.isTrue(generatedClient.getCedula().equals("123456789"), "Validacion de cedula");
		Assert.isTrue(generatedClient.getTelefono().equals("12345678"), "Validacion de telefono");
		Assert.isTrue(!generatedClient.isEstado(), "Validacion de estado");

		generatedClient.setEstado(true);
		generatedClient.setTelefono("222");
		generatedClient = clienteService.updateClient(generatedClient);

		Assert.isTrue(generatedClient.isEstado(), "Validacion de estado");
		Assert.isTrue(generatedClient.getTelefono().equals("222"), "Validacion de telefono");
	}


	@Test
	@Order(5)
	void getClientUsingCountryCodeWithActiveAccounts() {
		var generatedClient = clienteService.getClientUsingCountryCodeWithActiveAccounts("CR");
		Assert.isTrue(generatedClient.size() == 1, "Validacion de cantidad");
	}

	@Test
	@Order(6)
	void getClientUsingCountryCodeWithActiveAccountsNotFound() {

		var generatedClient = clienteService.getClientUsingCountryCodeWithActiveAccounts("GT");

		Assert.isTrue(generatedClient.size() == 0, "Validacion de cantidad");
	}

	@Test
	@Order(7)
	void obtenerClientesPorCodigoISOPaisYCuentasActivas() {

		var generatedClient = clienteService.obtenerClientesPorCodigoISOPaisYCuentasActivas("HN");

		Assert.isTrue(generatedClient.size() == 1, "Validacion de cantidad");
	}

	@Test
	@Order(8)
	void deleteClient() {

		var generatedClient = clienteService.getClient(1);

		Assert.isTrue(generatedClient.getId() == 1, "Validacion de id");
		Assert.isTrue(generatedClient.getApellidos().equals("PEREZ"), "Validacion de apellidos");
		Assert.isTrue(generatedClient.getNombre().equals("ROBERTO"), "Validacion de nombre");
		Assert.isTrue(generatedClient.getCedula().equals("1"), "Validacion de cedula");
		Assert.isTrue(generatedClient.getTelefono().equals("093939393"), "Validacion de telefono");

		clienteService.deleteClient(1);

		try{
			generatedClient = clienteService.getClient(1);
			Assert.isNull(generatedClient, "Encontró un registro");
		}
		catch (RuntimeException ex)
		{
			Assert.isTrue(ex.getMessage().equals("Cliente no existe"), "Validacion de not found");
		}
	}


	@Test
	@Order(9)
	void obtieneClientesPorApellidoQueryLanguage() {

		var clients = clienteService.obtieneClientesPorApellidoQueryLanguage("SANCHEZ");

		Assert.isTrue(clients.size() == 4, "Validacion de existecias");
	}

	@Test
	@Order(10)
	void obtieneClientesPorApellidoQueryLanguageNativeQuery() {

		var clients = clienteService.obtieneClientesPorApellidoQueryLanguageNativeQuery("SANCHEZ");

		Assert.isTrue(clients.size() == 4, "Validacion de existecias");
	}

	@Test
	@Order(11)
	void obtieneClientesExtrajerosConTarjetasInactivas() {

		var clients = clienteService.obtieneClientesExtrajerosConTarjetasInactivas("CR");

		Assert.isTrue(clients.size() == 2, "Validacion de existecias");
	}
}
