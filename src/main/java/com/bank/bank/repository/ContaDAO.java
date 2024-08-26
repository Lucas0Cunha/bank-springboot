package com.bank.bank.repository;


import com.bank.bank.dto.ContaResponseAgenciaDTO;
import com.bank.bank.models.Contas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaDAO extends JpaRepository<Contas, Long> {

    @Query(value = "SELECT cliente.nome,cliente.cpf_cnpj, contas.tipo_conta\n" +
            "FROM cliente\n" +
            "INNER JOIN contas ON cliente.id = contas.id_cliente", nativeQuery = true)
    List<String> getAllContas();

    @Query(value = "SELECT c.* FROM cliente INNER JOIN contas c ON cliente.id= c.id_cliente WHERE cliente.id = :clienteId", nativeQuery = true)
    List<Contas> getContaType(@Param("clienteId") Long clienteId);

    @Query(value = "SELECT cli.nome, SUM(c.saldo) FROM cliente cli INNER JOIN contas c ON cli.id= c.id_cliente WHERE cli.id = :clienteId  GROUP BY cli.id", nativeQuery = true)
    List<Object[]> getContaValue(@Param("clienteId") Long clienteId);

    @Query(value = "SELECT cli.nome, SUM(c.saldo) FROM cliente cli INNER JOIN contas c ON cli.id= c.id_cliente GROUP BY cli.id", nativeQuery = true)
    List<Object[]> getContaValueAll();


    @Query(value = "SELECT contas.id,contas.numero,contas.id_agencia,contas.tipo_conta, agencia.nome_agencia FROM contas INNER JOIN agencia ON agencia.id= contas.id_agencia WHERE contas.id_agencia=:agenciaId",nativeQuery = true)
    List<Object[]> getContasByAgencia(@Param("agenciaId") Long agenciaId);



}
