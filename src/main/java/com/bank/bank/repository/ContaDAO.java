package com.bank.bank.repository;

import com.bank.bank.models.Contas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaDAO extends JpaRepository<Contas, Long> {

    @Query(value = "SELECT cliente.nome,cliente.cpf_cnpj, contas.tipo_conta\n" +
            "FROM cliente\n" +
            "INNER JOIN contas ON cliente.id = contas.id_cliente", nativeQuery = true)
    List<String> getAllContas();

}
