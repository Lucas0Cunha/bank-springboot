package com.bank.bank.repository;

import com.bank.bank.models.Agencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenciasDAO extends JpaRepository<Agencias, Long> {

    @Query(value = "SELECT a.nomeAgencia FROM Agencias a", nativeQuery = true)
    List<String> getAllNames();
}
