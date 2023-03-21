package com.company.demodata.criteria;

import com.company.demodata.dto.ClienteDto;
import com.company.demodata.model.Cliente;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.javapoet.TypeName;
import org.springframework.stereotype.Component;

import javax.sound.midi.SysexMessage;
import java.text.MessageFormat;
import java.util.Locale;

@Component
public class ClienteSpecification extends SpecificationBase {

    private Specification<Cliente> apellidoCriteria(ClienteDto clienteDto)
    {
        return like("apellidos", clienteDto.getApellidos());
    }

    private Specification<Cliente> nombreCriteria(ClienteDto clienteDto)
    {
        return like("nombre", clienteDto.getNombre());
    }

    private Specification<Cliente> cedulaCriteria(ClienteDto clienteDto)
    {
        return like("cedula", clienteDto.getCedula());
    }

    private Specification<Cliente> telefonoCriteria(ClienteDto clienteDto)
    {
        return like("telefono", clienteDto.getTelefono());
    }

    private Specification<Cliente> paisNacimientoCriteria(ClienteDto clienteDto)
    {
        return like("paisNacimiento", clienteDto.getPaisNacimiento());
    }

    public Specification<Cliente> buildFilter(ClienteDto clienteDto)
    {
        System.out.println("");
        System.out.println(
        String.format("Evaluación de criterio para %1$s - %2$s - %3$s - %4$s - %5$s - %6$s",
                TypeName.get(ClienteDto.class),
                clienteDto.getApellidos(),
                clienteDto.getNombre(),
                clienteDto.getCedula(),
                clienteDto.getTelefono(),
                clienteDto.getPaisNacimiento())
        );
        System.out.println("");
        return Specification.where(apellidoCriteria(clienteDto))
                .and(nombreCriteria(clienteDto))
                .and(cedulaCriteria(clienteDto))
                .and(telefonoCriteria(clienteDto))
                .and(paisNacimientoCriteria(clienteDto));
    }
}
