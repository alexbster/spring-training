package com.company.demodata.service;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.model.Cuenta;
import com.company.demodata.repository.CuentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

@SpringBootTest
@Slf4j
class ClienteServiceTests {

	@Autowired
	private ClienteService clienteService;

	@Test
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
	void deleteClient() {
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

		clienteService.deleteClient(clienteDto.getId());

		try{
			generatedClient = clienteService.getClient(clienteDto.getId());
			Assert.isNull(generatedClient, "Encontró un registro");
		}
		catch (RuntimeException ex)
		{
			Assert.isTrue(ex.getMessage().equals("Cliente no existe"), "Validacion de not found");
		}
	}


	@Test
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
	@Sql({"classpath:datainicial.sql"})
	void getClientUsingCountryCodeWithActiveAccounts() {
		var generatedClient = clienteService.getClientUsingCountryCodeWithActiveAccounts("CR");
		Assert.isTrue(generatedClient.size() == 2, "Validacion de cantidad");
	}

	@Test
	@Sql({"classpath:datainicial.sql"})
	void getClientUsingCountryCodeWithActiveAccountsNotFound() {

		var generatedClient = clienteService.getClientUsingCountryCodeWithActiveAccounts("HN");

		Assert.isTrue(generatedClient.size() == 0, "Validacion de cantidad");
	}
}
