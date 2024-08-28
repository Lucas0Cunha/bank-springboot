package com.bank.bank.repository;

import com.bank.bank.dto.AgenciaDTO;
import static org.assertj.core.api.Assertions.assertThat;
import com.bank.bank.models.Agencias;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;



@DataJpaTest
@ActiveProfiles("test")
class AgenciasDAOTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    AgenciasDAO agenciasrepository;

    private Agencias createAgencia(AgenciaDTO agenciaDTO){
    Agencias newAgencia = new Agencias();
    this.entityManager.persist(newAgencia);
    return newAgencia;
    }


    @Test
    @DisplayName("Should return successfully from DB")
    void getAllNames() {
        AgenciaDTO agenciaDTO = new AgenciaDTO(1L,"Bahia","Salvador");
        this.createAgencia(agenciaDTO);
        Optional<Agencias>getAllNames = this.agenciasrepository.findById(1L);

        assertThat(getAllNames.isPresent()).isTrue();

    }


    @Test
    @DisplayName("Should not return successfully from DB")
    void getAllNames2() {
        AgenciaDTO agenciaDTO = new AgenciaDTO(1L,"Bahia","Salvador");

        Optional<Agencias>getAllNames = this.agenciasrepository.findById(1L);

        assertThat(getAllNames.isEmpty()).isTrue();

    }
}