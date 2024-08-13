package com.bank.bank.repository;

import com.bank.bank.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository //
public interface ClienteDAO extends JpaRepository<Cliente,Long> {



}
