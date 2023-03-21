package com.company.demodata.repository;

import com.company.demodata.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
    void deleteAllByCliente_Id(int clienteId);

    List<Tarjeta> findTarjetaByClienteIdAndEstadoIsTrue(int cliente);

    Tarjeta findTarjetaById(int id);

    @Modifying
    @Query("UPDATE Tarjeta t SET t.estado = :estado WHERE t.id = :id")
    void actualizarEstadoUsandoId(int id, boolean estado);
}
