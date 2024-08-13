package com.bank.bank.service.impl;

import com.bank.bank.dto.ContaRequestDTO;
import com.bank.bank.models.*;
import com.bank.bank.repository.ClienteDAO;
import com.bank.bank.repository.ContaDAO;
import com.bank.bank.service.ContaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired //
    private ContaDAO contaRepository;

    @Autowired
    private ClienteDAO clienteDAO;


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
        Cliente cliente = clienteDAO.getById(contaRequestDTO.idCliente());
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


    @Override
    @Transactional
    public void add(ContaRequestDTO contaDTO) {
        Contas c = this.contaFactory(contaDTO);
        Cliente cliente = clienteDAO.getById(contaDTO.idCliente());
        contaRepository.save(c);

    }

    private Contas contaFactory(ContaRequestDTO contaDTO) {
        Cliente cliente =new Cliente();

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
