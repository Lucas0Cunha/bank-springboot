package com.bank.bank.repository;

import com.bank.bank.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Define para o spring boot q esse é o Repository para o banco de dados reconhecer
public interface ClienteDAO extends JpaRepository<Cliente, Long> {

    // funciona como a query do banco de dados, simplifica o processo, outra forma de fazer
    @Query (value = "SELECT c.nome FROM Cliente c", nativeQuery = true)
    List<String> getAllNames();



}
