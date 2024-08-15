package com.bank.bank.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.bank.dto.ContaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<ContaResponseDTO> findAll() {
        List<Contas> contas = contaRepository.findAll();
        List<ContaResponseDTO> responseDTOS = new ArrayList<>();
        for (Contas c : contas) {
            ContaResponseDTO responseDTO = new ContaResponseDTO(c.getSaldo(), c.getCliente().getNome());
            responseDTOS.add(responseDTO);
        }
        return responseDTOS;
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

    //
    @Override
    public ContaResponseDTO getById(Long id) {
        Optional<Contas> contas = contaRepository.findById(id);
        if (contas.isPresent()) {
            ContaResponseDTO responseDTO = new ContaResponseDTO(contas.get().getSaldo(), contas.get().getCliente().getNome());
            return responseDTO;
        }
        return null;
    }

    @Transactional
    @Override
    public void add(ContaRequestDTO contaDTO) {
        Contas c = this.contaFactory(contaDTO);
        contaRepository.save(c);

    }

    @Override
    public List<String> getAllContas() {
        return contaRepository.getAllContas();

    }

    private Contas contaFactory(ContaRequestDTO contaDTO) {
        Cliente cliente = clienteService.getById(contaDTO.idCliente());

        if (contaDTO.saldo() > 200) {
            return new ContaSalario(contaDTO.numero(), cliente, contaDTO.saldo());
        } else if (contaDTO.saldo() > 100) {
            return new ContaPoupanca(contaDTO.numero(), cliente, contaDTO.saldo());
        } else if (contaDTO.saldo() == 0) {
            //double limiteCartao = calcularLimite(contaDTO.saldo());
            LocalDate dataValidade = LocalDate.now().plusYears(5);
            return new ContaCredito(contaDTO.numero(), cliente, 0, /*limiteCartao,*/ dataValidade);
        }
        return null;
    }

    /*private double calcularLimite(double saldo) {
        return saldo * 1.5;

    }*/
}
