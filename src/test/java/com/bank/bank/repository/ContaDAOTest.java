package com.bank.bank.repository;


import com.bank.bank.models.Agencias;
import com.bank.bank.models.Cliente;

import com.bank.bank.models.ContaSalario;
import com.bank.bank.models.Contas;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;


@DataJpaTest
@ActiveProfiles("test")
class ContaDAOTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ContaDAO contaRepository;

    @Autowired
    AgenciasDAO agenciaRepository;

    @Autowired
    ClienteDAO clienteRepository;

    private Agencias agencias;

    private Cliente cliente;

    @BeforeEach
    void setup() {
        Cliente clienteTeste = new Cliente();
        Agencias agenciasTeste = new Agencias();
        clienteTeste.setCpfCnpj("5616");
        clienteTeste.setEmail("efweff");
        clienteTeste.setNome("sdwedf");
        agenciasTeste.setNomeAgencia("São Paulo");
        agenciasTeste.setEstado("SSSSS");

        agencias = agenciaRepository.save(agenciasTeste);
        cliente = clienteRepository.save(clienteTeste);

    }

    @Test
    void getAllContas() {
        ContaSalario contaSalario = new ContaSalario();
        contaSalario.setNumero("5616");
        contaSalario.setAgencias(agencias);
        contaSalario.setCliente(cliente);
        contaSalario.setSaldo(6262);
        contaRepository.save(contaSalario);

        List<String> getAll = contaRepository.getAllContas();

        assertThat(getAll).isNotNull();

    }

    @Test
    void getAllContas2() {
        ContaSalario contaSalario = new ContaSalario();
        contaSalario.setNumero("5616");
        contaSalario.setAgencias(agencias);
        contaSalario.setCliente(cliente);
        contaSalario.setSaldo(6262);


        List<String> getAll = contaRepository.getAllContas();

        assertThat(getAll.isEmpty());

    }

    @Test
    void getContaType() {
        ContaSalario contaSalario = new ContaSalario();
        contaSalario.setNumero("5616");
        contaSalario.setAgencias(agencias);
        contaSalario.setCliente(cliente);
        contaSalario.setSaldo(6262);
        contaRepository.save(contaSalario);

        List<Contas> getConta = contaRepository.getContaType(cliente.getId());

        assertThat(getConta).isNotNull();
    }


    @Test
    void getContaType2() {
        ContaSalario contaSalario = new ContaSalario();
        contaSalario.setNumero("5616");
        contaSalario.setAgencias(agencias);
        contaSalario.setCliente(cliente);
        contaSalario.setSaldo(6262);

        List<Contas> getConta = contaRepository.getContaType(cliente.getId());

        assertThat(getConta).isEmpty();
    }


    @Test
    void getContaValue() {
        ContaSalario contaSalario = new ContaSalario();
        contaSalario.setNumero("5616");
        contaSalario.setAgencias(agencias);
        contaSalario.setCliente(cliente);
        contaSalario.setSaldo(6262);
        contaRepository.save(contaSalario);
        List<Object[]> getConta = contaRepository.getContaValue(cliente.getId());

        assertThat(getConta).isNotNull();
    }

    @Test
    void getContaValue2() {
        ContaSalario contaSalario = new ContaSalario();
        contaSalario.setNumero("5616");
        contaSalario.setAgencias(agencias);
        contaSalario.setCliente(cliente);
        contaSalario.setSaldo(6262);

        List<Object[]> getConta = contaRepository.getContaValue(cliente.getId());

        assertThat(getConta).isEmpty();
    }

    @Test
    void getContaValueAll() {
        ContaSalario contaSalario = new ContaSalario();
        contaSalario.setNumero("5616");
        contaSalario.setAgencias(agencias);
        contaSalario.setCliente(cliente);
        contaSalario.setSaldo(6262);

        List<Object[]> getConta = contaRepository.getContaValueAll();
        assertThat(getConta).isEmpty();
    }

    @Test
    void getContaValueAll2() {
        ContaSalario contaSalario = new ContaSalario();
        contaSalario.setNumero("5616");
        contaSalario.setAgencias(agencias);
        contaSalario.setCliente(cliente);
        contaSalario.setSaldo(6262);
        contaRepository.save(contaSalario);

        List<Object[]> getConta = contaRepository.getContaValueAll();
        assertThat(getConta).isNotNull();
    }

    @Test
    void getContasByAgencia() {
        ContaSalario contaSalario = new ContaSalario();
        contaSalario.setNumero("5616");
        contaSalario.setAgencias(agencias);
        contaSalario.setCliente(cliente);
        contaSalario.setSaldo(6262);
        contaRepository.save(contaSalario);

        List<Object[]> getConta = contaRepository.getContasByAgencia(agencias.getId());
        assertThat(getConta).isNotNull();
    }

    @Test
    void getContasByAgencia2() {
        ContaSalario contaSalario = new ContaSalario();
        contaSalario.setNumero("5616");
        contaSalario.setAgencias(agencias);
        contaSalario.setCliente(cliente);
        contaSalario.setSaldo(6262);


        List<Object[]> getConta = contaRepository.getContasByAgencia(agencias.getId());
        assertThat(getConta).isEmpty();
    }
}