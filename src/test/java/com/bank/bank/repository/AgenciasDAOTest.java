package com.bank.bank.repository;

import com.bank.bank.dto.AgenciaTestDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bank.bank.models.Agencias;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;



@DataJpaTest
@ActiveProfiles("test")
class AgenciasDAOTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    AgenciasDAO agenciasrepository;


    @Test
    @DisplayName("Should return successfully from DB")
    void getAllNames() {
        Agencias agencias = new Agencias();
        agencias.setNomeAgencia("efsef");
        agencias.setEstado("efsef");

        agenciasrepository.save(agencias);

        List<String> allNames = agenciasrepository.getAllNames();

        // Check that the list is not empty
        assertFalse(allNames.isEmpty());

    }


    @Test
    @DisplayName("Should not return successfully from DB")
    void getAllNames2() {
        Agencias agencias = new Agencias();
        agencias.setNomeAgencia("efsef");
        agencias.setEstado("efsef");

        List<String> getAllNames = this.agenciasrepository.getAllNames();

        assertTrue(getAllNames.isEmpty());

    }
}