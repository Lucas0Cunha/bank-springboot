package com.bank.bank.repository;


import com.bank.bank.models.Agencias;
import com.bank.bank.models.Cliente;
import com.bank.bank.models.ContaSalario;
import com.bank.bank.models.Contas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class ClienteDAOTest {


    @Autowired
    ClienteDAO clienteRepository;

    @Autowired
    ContaDAO contaRepository;


    @Autowired
    AgenciasDAO agenciaRepository;


    private Contas contastest;
    private Agencias agenciastest;

    @BeforeEach
    void setup() {

        ContaSalario contas = new ContaSalario();
        contas.setSaldo(651);
        contas.setNumero("21321");

        Agencias agenciasTeste = new Agencias();
        agenciasTeste.setNomeAgencia("São Paulo");
        agenciasTeste.setEstado("SSSSS");

        Agencias agencias = agenciaRepository.save(agenciasTeste);
        contas.setAgencias(agencias);

        agenciastest = agenciaRepository.save(agenciasTeste);
        contastest = contaRepository.save(contas);
    }



    @Test
    @DisplayName("Should not return successfully from DB")
    void getInfoClienteConta() {

        assertTrue(clienteRepository.getInfoClienteConta(1L).isEmpty());
    }

    @Test
    @DisplayName("Should return successfully from DB")
    void getInfoClienteConta2() {
        Cliente cliente = new Cliente();
        cliente.setNome("lucas");
        cliente.setEmail("efefw");
        cliente.setCpfCnpj("65167");
        clienteRepository.save(cliente);


        List<Object[]> infoClienteConta = clienteRepository.getInfoClienteConta(1L);


        assertThat(infoClienteConta).isNotNull();
    }

    @Test
    @DisplayName("Should return successfully from DB")
    void getAllNames() {
        Cliente cliente = new Cliente();
        cliente.setNome("lucas");
        cliente.setEmail("efefw");
        cliente.setCpfCnpj("65162");

        clienteRepository.save(cliente);

        assertThat(clienteRepository.getAllNames().isEmpty());
    }

    @Test
    @DisplayName("Should not return successfully from DB")
    void getAllNamesFalse() {
        Cliente cliente = new Cliente();
        cliente.setNome("lucas");
        cliente.setEmail("efefw");
        cliente.setCpfCnpj("65162");

        assertThat(clienteRepository.getAllNames().isEmpty());
    }


    @Test
    @DisplayName("Should return successfully from DB")
    void getInfoClienteCredito() {
        Cliente cliente = new Cliente();
        cliente.setNome("lucas");
        cliente.setEmail("efefw");
        cliente.setCpfCnpj("65162");
        clienteRepository.save(cliente);

        List<Object[]> infoClienteConta = clienteRepository.getInfoClienteCredito();
        assertThat(infoClienteConta).isNotNull();
    }

    @Test
    @DisplayName("Should not return successfully from DB")
    void getInfoClienteCredito2() {
        Cliente cliente = new Cliente();
        cliente.setNome("lucas");
        cliente.setEmail("efefw");
        cliente.setCpfCnpj("65162");


        assertThat(clienteRepository.getInfoClienteCredito().isEmpty());
    }

    @Test
    @DisplayName("Should not return successfully from DB")
    void getInfoClienteLocal() {
        Cliente cliente = new Cliente();
        cliente.setNome("lucas");
        cliente.setEmail("efefw");
        cliente.setCpfCnpj("65162");


        assertThat(clienteRepository.getInfoClienteLocal().isEmpty());
    }

    @Test
    @DisplayName("Should return successfully from DB")
    void getInfoClienteLocal2() {
        Cliente cliente = new Cliente();
        cliente.setNome("lucas");
        cliente.setEmail("efefw");
        cliente.setCpfCnpj("65162");

        clienteRepository.save(cliente);
        List<Object[]> infoClienteConta = clienteRepository.getInfoClienteLocal();
        assertThat(infoClienteConta).isNotNull();

    }
}