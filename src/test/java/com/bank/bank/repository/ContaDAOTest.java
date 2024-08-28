package com.bank.bank.repository;



import com.bank.bank.dto.ContaTestDTO;
import com.bank.bank.models.Agencias;
import com.bank.bank.models.Cliente;
import com.bank.bank.ContaTest;
import com.bank.bank.models.Contas;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;


@DataJpaTest
@ActiveProfiles("test")
class ContaDAOTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ContaDAO contaRepository;


    private Contas createConta(ContaTestDTO contaTestDTO) {
        Contas newConta = new ContaTest(contaTestDTO);
        this.entityManager.persist(newConta);

        return newConta;
    }


    @Test
    void getAllContas() {
    }

    @Test
    void getContaType() {
        ContaTestDTO contaTestDTO = new ContaTestDTO(100L,32,"22",new Agencias(),new Cliente());
        this.createConta(contaTestDTO);

        List<Contas> contas= this.contaRepository.getContaType(contaTestDTO.id());

        assertThat(contas.isEmpty());
    }

    @Test
    void getContaValue() {
    }

    @Test
    void getContaValueAll() {
    }

    @Test
    void getContasByAgencia() {
    }
}