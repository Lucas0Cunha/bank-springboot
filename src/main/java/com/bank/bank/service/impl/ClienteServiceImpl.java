package com.bank.bank.service.impl;

import com.bank.bank.dto.ClienteRequestDTO;
import com.bank.bank.models.Cliente;
import com.bank.bank.repository.ClienteDAO;
import com.bank.bank.service.ClienteService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //
public class ClienteServiceImpl implements ClienteService {
    @Autowired //
    private ClienteDAO clienteRepository;

    @Override
    public void add(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional // Como tem mais de uma ida a base de dados precisa de um transactional
    public void update(Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteGet = clienteRepository.findById(id).get();
        if (clienteGet != null) {
            clienteGet.setNome(clienteRequestDTO.nome());
            clienteGet.setEmail(clienteRequestDTO.email());
            clienteGet.setCpfCnpj(clienteRequestDTO.cpfCnpj());

            clienteRepository.save(clienteGet);
        }
    }

    @Override
    public Cliente getById(Long id) {
        return clienteRepository.findById(id).get();
    }


}
