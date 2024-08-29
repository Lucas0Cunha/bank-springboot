package com.bank.bank.repository;


import com.bank.bank.dto.ClienteTestDTO;
import com.bank.bank.models.Cliente;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ClienteDAOTest {


    @Autowired
    EntityManager entityManager;

    @Autowired
    ClienteDAO clienteRepository;

    private Cliente createCliente(ClienteTestDTO clienteTestDTO) {
        Cliente newCliente = new Cliente(clienteTestDTO);
        this.clienteRepository.save(newCliente);

        return newCliente;
    }

    @Test
    void getInfoClienteConta() {
        Long id = 652321L;
        ClienteTestDTO clienteTestDTO = new ClienteTestDTO(id, "lucas@...", "511", "511","São Paulo");
        this.createCliente(clienteTestDTO);
        Optional<Cliente> clienteGet = this.clienteRepository.findById(id);

        assertThat(clienteGet.isPresent()).isTrue();
    }


}