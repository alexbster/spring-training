package com.company.demodata.soap;
import com.company.demodata.service.ClienteService;
import com.company.demodata.service.Helpers;
import io.spring.guides.gs_producing_web_service.GetClienteRequest;
import io.spring.guides.gs_producing_web_service.GetClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClienteEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private ClienteService clienteService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClienteRequest")
    @ResponsePayload
    public GetClienteResponse getCliente(@RequestPayload GetClienteRequest request) {
        var cliente = clienteService.getClient(request.getId());
        var soapClient = new io.spring.guides.gs_producing_web_service.Cliente();
        soapClient.setId(cliente.getId());
        soapClient.setApellidos(cliente.getApellidos());
        soapClient.setNombre(cliente.getNombre());
        soapClient.setCedula(cliente.getCedula());
        soapClient.setTelefono(cliente.getTelefono());
        soapClient.setPaisNacimiento(cliente.getPaisNacimiento());
        var response = new GetClienteResponse();
        response.setCliente(soapClient);
        return response;
    }
}
