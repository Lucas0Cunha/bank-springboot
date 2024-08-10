package com.bank.bank.service.impl;

import com.bank.bank.models.Cliente;
import com.bank.bank.repository.ClienteRepository;
import com.bank.bank.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //
public class ClienteServiceImpl implements ClienteService {
    @Autowired //
    private ClienteRepository clienteRepository;

    @Override
    public void add(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> findAll() {
       return clienteRepository.findAll();
    }

    @Override
    public void delete(String cpfCnpj) {
        Optional<Cliente> cliente = clienteRepository.findByCpfCnpj(cpfCnpj);
        if (cliente.isPresent()) {
            clienteRepository.deleteByCpfCnpj(cpfCnpj);
        } else {
            throw new EntityNotFoundException("Cliente com CPF/CNPJ " + cpfCnpj + " não encontrado.");
        }
    }

    @Override
    public void update(Cliente cliente) {
        clienteRepository.save(cliente);
    }


}
