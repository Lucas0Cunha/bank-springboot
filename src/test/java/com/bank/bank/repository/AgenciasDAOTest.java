package com.bank.bank.repository;

import com.bank.bank.dto.AgenciaTestDTO;
import static org.assertj.core.api.Assertions.assertThat;
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

    private Agencias createAgencia(AgenciaTestDTO agenciaTestDTO){
    Agencias newAgencia = new Agencias();
    this.entityManager.persist(newAgencia);
    return newAgencia;
    }


    @Test
    @DisplayName("Should return successfully from DB")
    void getAllNames() {
        AgenciaTestDTO agenciaTestDTO = new AgenciaTestDTO(1L,"Bahia","Salvador");
        this.createAgencia(agenciaTestDTO);
        List<String> getAllNames = this.agenciasrepository.getAllNames();

        assertThat(getAllNames.isEmpty()).isFalse();

    }


    @Test
    @DisplayName("Should not return successfully from DB")
    void getAllNames2() {
        AgenciaTestDTO agenciaTestDTO = new AgenciaTestDTO(1L,"Bahia","Salvador");

        List<String> getAllNames = this.agenciasrepository.getAllNames();

        assertThat(getAllNames.isEmpty()).isTrue();

    }
}