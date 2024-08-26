package com.bank.bank.repository;

import com.bank.bank.dto.ClienteResponseDTOContas;
import com.bank.bank.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Define para o spring boot q esse é o Repository para o banco de dados reconhecer
public interface ClienteDAO extends JpaRepository<Cliente, Long> {

    // funciona como a query do banco de dados, simplifica o processo, outra forma de fazer
    @Query (value = "SELECT c.nome FROM Cliente c", nativeQuery = true)
    List<String> getAllNames();


    @Query (value = "SELECT cli.nome, a.nome_agencia, cli.estado FROM cliente cli INNER JOIN contas co ON  cli.id= co.id_cliente INNER JOIN agencia a ON co.id_agencia=a.id WHERE cli.id=:clienteId",nativeQuery = true)
    List<Object[]> getInfoClienteConta(@Param("clienteId") Long idCliente);

    @Query(value = "SELECT cli.nome, co.tipo_conta FROM cliente cli INNER JOIN contas co ON  cli.id= co.id_cliente WHERE co.tipo_conta='contacredito'",nativeQuery = true)
    List<Object[]> getInfoClienteCredito();


    @Query(value = "SELECT cli.nome AS Name,cli.estado AS CurrentAdress,a.estado AS AgencyLocal ,a.nome_agencia AS AgencyName FROM cliente cli INNER JOIN contas c ON cli.id= c.id_cliente INNER JOIN agencia a ON c.id_agencia= a.id WHERE cli.estado!=a.estado",nativeQuery = true)
    List<Object[]> getInfoClienteLocal();
}
