package com.bank.bank.service;

import com.bank.bank.dto.ClienteRequestDTO;
import com.bank.bank.models.Cliente;

import java.util.List;

public interface ClienteService {
    public void add (Cliente cliente);
    public List<Cliente> findAll ();
    public void delete (Long id);
    public void update (Long id, ClienteRequestDTO clienteRequestDTO);
    public Cliente getById (Long id);
    public List<String> getAllNames();

}
