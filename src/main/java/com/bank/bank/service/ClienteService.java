package com.bank.bank.service;

import com.bank.bank.dto.ClienteRequestDTO;
import com.bank.bank.dto.ClienteRequestDTOCredito;
import com.bank.bank.dto.ClienteRequestDTOLocal;
import com.bank.bank.dto.ClienteResponseDTOContas;
import com.bank.bank.models.Cliente;
import com.bank.bank.models.Contas;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ClienteService {
    public void add(Cliente cliente);

    public List<Cliente> findAll();

    public void delete(Long id);

    public void update(Long id, ClienteRequestDTO clienteRequestDTO);

    public Cliente getById(Long id);

    public List<String> getAllNames();


    @Transactional
        // Como tem mais de uma ida a base de dados precisa de um transactional
    List<Contas> getInfoCliente(Long id);

    @Transactional
        // Como tem mais de uma ida a base de dados precisa de um transactional
    List<String> getSaldoCliente(Long id);

    @Transactional
    List<ClienteResponseDTOContas> getInfoClienteConta(Long idCliente);

    @Transactional
    List<ClienteRequestDTOCredito> getInfoClienteCredito();

    @Transactional
    List<ClienteRequestDTOLocal> getInfoClienteLocal();
}
