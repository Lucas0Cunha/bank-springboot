package com.bank.bank.service.impl;

import com.bank.bank.dto.*;
import com.bank.bank.models.*;
import com.bank.bank.repository.ClienteDAO;
import com.bank.bank.repository.ContaDAO;
import com.bank.bank.service.ClienteService;

import com.bank.bank.util.TipoConta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //
public class ClienteServiceImpl implements ClienteService {
    @Autowired //
    private ClienteDAO clienteRepository;

    @Autowired
    private ContaDAO contaRepository;

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

    @Override
    public List<ClienteResponseDTOContas> getInfoClienteConta(Long idCliente){
        List<Object[]> lista = clienteRepository.getInfoClienteConta(idCliente);
        List<ClienteResponseDTOContas> listReturn = new ArrayList<>();
        if (lista != null) {
            lista.forEach(o -> {
                String nome = o[0].toString();
                String nomeAgencia = o[1].toString(); // Converte para String
                String estado = o[2].toString();
                listReturn.add(new ClienteResponseDTOContas(nome, nomeAgencia,estado));
            });

            return listReturn;
        }
        return null;
    }

    @Override
    public List<ClienteRequestDTOCredito> getInfoClienteCredito() {
        List<Object[]> lista = clienteRepository.getInfoClienteCredito();
        List<ClienteRequestDTOCredito> listReturn = new ArrayList<>();
        if (lista != null) {
            lista.forEach(o -> {
                String nome = o[0].toString();
                String tipoConta = o[1].toString(); // Converte para String

                listReturn.add(new ClienteRequestDTOCredito(nome,tipoConta));
            });

            return listReturn;
        }
        return null;
    }


    @Override
    public List<ClienteRequestDTOLocal> getInfoClienteLocal() {
        List<Object[]> lista = clienteRepository.getInfoClienteLocal();
        List<ClienteRequestDTOLocal> listReturn = new ArrayList<>();
        if (lista != null) {
            lista.forEach(o -> {
                String nome = o[0].toString();
                String estadoCliente = o[1].toString();
                String estadoAgencia = o[2].toString();
                String nomeAgencia = o[3].toString();
                listReturn.add(new ClienteRequestDTOLocal(nome,estadoCliente,estadoAgencia,nomeAgencia));
            });

            return listReturn;
        }
        return null;
    }


    @Override
    public List<String> getAllNames() {
        return clienteRepository.getAllNames();
    }




    public TipoConta gettipoConta(Long id) {
        Contas contaGet = contaRepository.findById(id).get();

        if (contaGet.getSaldo() > 200) {
            return TipoConta.SALARIO;
        } else if (contaGet.getSaldo() > 100) {
            return TipoConta.DEBITO;
        } else if (contaGet.getSaldo() == 0) {

            return TipoConta.CREDITO;
        }
        return null;
    }

    @Override
    @Transactional // Como tem mais de uma ida a base de dados precisa de um transactional
    public List<Contas> getInfoCliente(Long id) {//id=1
    List<Contas> getContaType = contaRepository.getContaType(id);

       /* List<Contas> contaGet = contaRepository.findAll();

        List<Contas> listaContas = new ArrayList<>();
        for (Contas contas : contaGet) {
            //for (int i=0; i<contaGet.size(); i++)
            if (contas.getCliente().getId().equals(id)) {
                listaContas.add(contas);
            }
        }*/

        return getContaType;
    }

    @Override
    public List<String> getSaldoCliente(Long id) {
        return List.of();
    }


   /* @Override
    @Transactional // Como tem mais de uma ida a base de dados precisa de um transactional
    public List<String> getSaldoCliente(Long id) {
        List<String> saldoNome = new ArrayList<>();
        Cliente clienteGet = clienteRepository.findById(id).get();
        Contas contaGet = contaRepository.findById(id).get();

        saldoNome.add(clienteGet.getNome());
        saldoNome.add(getSaldoTotal(id));


        return saldoNome;

    }


    /*public String getSaldoTotal(Long id) {
        List<Double> saldos = new ArrayList<>();
        Contas contaGet = contaRepository.findById(id).get();

        ContaSalario contaSalario = contaRepository.findById(id).get();
        ContaDebito contaDebito = contaRepository.findById(id).get();
        ContaPoupanca contaPoupanca = contaRepository.findById(id).get();


        if (contaSalario != null && contaSalario.getSaldo() >= 0) {
            saldos.add(contaSalario.getSaldo());
        }

        // Adiciona o saldo da ContaDebito se for >= 0
        if (contaDebito != null && contaDebito.getSaldo() >= 0) {
            saldos.add(contaDebito.getSaldo());
        }

        // Adiciona o saldo da ContaPoupanca se for >= 0
        if (contaPoupanca != null && contaPoupanca.getSaldo() >= 0) {
            saldos.add(contaPoupanca.getSaldo());
        }

        return saldos.toString();
    }*/
}
