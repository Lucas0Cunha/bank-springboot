package com.bank.bank.service;

import com.bank.bank.dto.AgenciaRequestDTO;


import com.bank.bank.models.Agencias;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AgenciaService {

    public void add(Agencias agencias);


    public List<Agencias> findAll();


    public void delete(Long id);


    @Transactional
        // Como tem mais de uma ida a base de dados precisa de um transactional
    void update(Long id, AgenciaRequestDTO agenciaRequestDTO);

    public Agencias getById(Long id);


    public List<String> getAllNames();


}
