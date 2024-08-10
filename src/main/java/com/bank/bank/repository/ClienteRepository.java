package com.bank.bank.repository;

import com.bank.bank.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    void deleteByCpfCnpj(String cpfCnpj);
    Optional<Cliente> findByCpfCnpj(String cpfCnpj);
}
