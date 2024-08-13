package com.bank.bank.service.impl;

import com.bank.bank.dto.ContaRequestDTO;
import com.bank.bank.models.ContaCredito;
import com.bank.bank.models.ContaPoupanca;
import com.bank.bank.models.ContaSalario;
import com.bank.bank.models.Contas;
import com.bank.bank.repository.ContaDAO;
import com.bank.bank.service.ContaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContaServiceImpl implements ContaService {

    @Autowired //
    private ContaDAO contaRepository;

    @Override
    public void add(Contas contas) {

        contaRepository.save(contas);
    }

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
        if (contasGet != null) {
            contasGet.setNumero(contaRequestDTO.numero());
            contasGet.setCpfCnpj(contaRequestDTO.cpfCnpj());
            contasGet.setSaldo(contaRequestDTO.saldo());

            contaRepository.save(contasGet);
        }
    }

    @Override
    public Contas getById(Long id) {
        return contaRepository.findById(id).get();
    }


    @Override
    public void add(ContaRequestDTO contaDTO) {
        Contas c = this.contaFactory(contaDTO);
        this.add(c);
    }

    private Contas contaFactory(ContaRequestDTO contaDTO) {
        if (contaDTO.saldo() > 200) {
            return new ContaSalario(contaDTO.numero(), contaDTO.cpfCnpj(), contaDTO.saldo());
        } else if (contaDTO.saldo() > 100) {
            return new ContaPoupanca(contaDTO.numero(), contaDTO.cpfCnpj(), contaDTO.saldo());
        } else if (contaDTO.saldo() == 0) {
            return new ContaCredito(contaDTO.numero(), contaDTO.cpfCnpj(),0);
        }
        return null;
    }






}
