package com.bank.bank.repository;

import com.bank.bank.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //
public interface ClienteDAO extends JpaRepository<Cliente, Long> {

    //
    @Query (value = "SELECT c.nome FROM Cliente c", nativeQuery = true)
    List<String> getAllNames();

}
