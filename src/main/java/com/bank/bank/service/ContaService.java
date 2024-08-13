package com.bank.bank.service;

import com.bank.bank.dto.ContaRequestDTO;
import com.bank.bank.models.Contas;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ContaService {


    public List<Contas> findAll();

    public void delete(Long id);

    @Transactional
        // Como tem mais de uma ida a base de dados precisa de um transactional
    void update(Long id, ContaRequestDTO contaRequestDTO);

    public Contas getById(Long id);

    void add(ContaRequestDTO contaDTO);
}
