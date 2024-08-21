package com.bank.bank.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bank.bank.dto.ContaResponseDTO;
import com.bank.bank.models.*;
import com.bank.bank.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.dto.ContaRequestDTO;
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

    @Autowired
    private AgenciaService agenciaService;


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
            //  contasGet.setLocal(contaRequestDTO.local());

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
        Agencias agencias = agenciaService.getById(contaDTO.idAgencia());


        if (contaDTO.saldo() > 200) {
            return new ContaSalario(contaDTO.numero(), cliente, contaDTO.saldo(),agencias);
        } else if (contaDTO.saldo() > 100) {
            return new ContaPoupanca(contaDTO.numero(), cliente, contaDTO.saldo(),agencias);
        } else if (contaDTO.saldo() == 0) {
            //double limiteCartao = calcularLimite(contaDTO.saldo());
            LocalDate dataValidade = LocalDate.now().plusYears(5);
            return new ContaCredito(contaDTO.numero(), cliente, 0, /*limiteCartao,*/ dataValidade,agencias);
        }

        return null;
    }

    @Override
    public ContaResponseDTO getContaValue(Long clienteId) {
        // Object recebe quaisquer valores
        List<Object[]> lista = contaRepository.getContaValue(clienteId);
        //Na lista de arrays, o primeiro index da lista contem um bloco de arrays vetoriais, que são uma lista propria
        if (lista != null) {
            String nome = lista.get(0)[0].toString(); // Converte para String
            // neste caso o lista.get pega o primeiro index da List e o vetorial pega o primeiro valor da lista de objeto, nesse caso
            // no getContaValue, o metodo graças a query me retorna na base de dados uma linha com 2 valores, o nome e o saldo, e é isso que é pego no index 0 e no vetor 0
            Double valor = Double.parseDouble(lista.get(0)[1].toString()); // Converte para Double
            // pego o saldo no vetor 2 do index 0 da lista
            return new ContaResponseDTO(valor, nome);
        }
        return null;
    }

    @Override
    public List<ContaResponseDTO> getContaValueAll() {
        List<Object[]> lista = contaRepository.getContaValueAll();
        List<ContaResponseDTO> listReturn = new ArrayList<>();
        if (lista != null) {

            /*for (int i = 0; i < lista.size(); i++) {
                Object[] o = lista.get(i);
            }
            for (Object[] o : lista) {

            }*/

            lista.forEach(o -> {
                String nome = o[0].toString(); // Converte para String
                Double valor = Double.parseDouble(o[1].toString());
                listReturn.add(new ContaResponseDTO(valor, nome));
            });

            return listReturn;
        }
        return null;
    }





    /*private double calcularLimite(double saldo) {
        return saldo * 1.5;

    }*/
}
