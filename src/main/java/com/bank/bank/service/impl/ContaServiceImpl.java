package com.bank.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.dto.ContaRequestDTO;
import com.bank.bank.models.Cliente;
import com.bank.bank.models.ContaCredito;
import com.bank.bank.models.ContaPoupanca;
import com.bank.bank.models.ContaSalario;
import com.bank.bank.models.Contas;
import com.bank.bank.repository.ContaDAO;
import com.bank.bank.service.ClienteService;
import com.bank.bank.service.ContaService;

import jakarta.transaction.Transactional;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired //
    private ContaDAO contaRepository;

    @Autowired
    private ClienteService clienteService;


    @Override
    public List<Contas> findAll() {
        return contaRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        contaRepository.deleteById(id);
    }

    @Override
    @Transactional // Como tem mais de uma ida a base de dados precisa de um transactional
    public void update(Long id, ContaRequestDTO contaRequestDTO) {
        Contas contasGet = contaRepository.findById(id).get();
        Cliente cliente = clienteService.getById(contaRequestDTO.idCliente());
        if (contasGet != null) {
            contasGet.setNumero(contaRequestDTO.numero());
            contasGet.setCliente(cliente);
            contasGet.setSaldo(contaRequestDTO.saldo());

            contaRepository.save(contasGet);
        }
    }

    @Override
    public Contas getById(Long id) {
        return contaRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void add(ContaRequestDTO contaDTO) {
        Contas c = this.contaFactory(contaDTO);
        contaRepository.save(c);

    }

    private Contas contaFactory(ContaRequestDTO contaDTO) {
    	Cliente cliente = clienteService.getById(contaDTO.idCliente());

        if (contaDTO.saldo() > 200) {
            return new ContaSalario(contaDTO.numero(), cliente, contaDTO.saldo());
        } else if (contaDTO.saldo() > 100) {
            return new ContaPoupanca(contaDTO.numero(),cliente, contaDTO.saldo());
        } else if (contaDTO.saldo() == 0) {
            return new ContaCredito(contaDTO.numero(), cliente,0);
        }
        return null;
    }






}
