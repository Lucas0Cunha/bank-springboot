package com.bank.bank.service;

import com.bank.bank.models.Cliente;

import java.util.List;

public interface ClienteService {
    public void add (Cliente cliente);
    public List<Cliente> findAll ();
    public void delete (String cpfCnpj);
    public void update (Cliente cliente);

}
