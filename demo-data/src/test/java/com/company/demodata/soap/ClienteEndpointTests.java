package com.company.demodata.soap;

import io.spring.guides.gs_producing_web_service.GetClienteRequest;
import io.spring.guides.gs_producing_web_service.GetClienteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteEndpointTests {
    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @LocalServerPort
    private int port = 0;

    @BeforeEach
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetClienteRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    //Send SOAP request to server and assert the response
    public void testGetCliente() throws Exception {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);
        GetClienteRequest request = new GetClienteRequest();
        request.setId(2);
        var response = (GetClienteResponse)webServiceTemplate.marshalSendAndReceive("http://localhost:" + port + "/ws", request);
        assert response.getCliente().getNombre().equals("RAUL");
        assert response.getCliente().getCedula().equals("2");
        assert response.getCliente().getApellidos().equals("SANCHEZ");

    }
}
