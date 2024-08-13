package com.bank.bank.repository;

import com.bank.bank.models.Contas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaDAO extends JpaRepository<Contas,Long> {
}
