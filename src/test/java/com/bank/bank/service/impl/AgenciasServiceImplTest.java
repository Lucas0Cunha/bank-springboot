package com.bank.bank.service.impl;

import com.bank.bank.exceptions.ListaVaziaException;
import com.bank.bank.models.Agencias;
import com.bank.bank.repository.AgenciasDAO;
import com.bank.bank.service.AgenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgenciasServiceImplTest {

    //
    @Mock
    AgenciasDAO agenciasRepository;

    //
    @InjectMocks
    AgenciasServiceImpl agenciaService;

    //
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllNames() {
        // Configura o mock para retornar uma lista simulada de nomes
        List<String> nomesSimulados = List.of("São Paulo", "Minas Gerais");
        when(agenciasRepository.getAllNames()).thenReturn(nomesSimulados);

        // Executa o método a ser testado
        List<String> resultado = agenciaService.getAllNames();

        // Verifica se o resultado está correto
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("São Paulo", resultado.get(0));
        assertEquals("Minas Gerais", resultado.get(1));
        verify(agenciasRepository, times(2)).getAllNames();
    }



    @Test
    void getAllNamesThrowsExceptionWhenEmpty() {
        // Configura o mock para retornar uma lista vazia
        when(agenciasRepository.getAllNames()).thenReturn(List.of());

        // Verifica se a exceção é lançada
        assertThrows(ListaVaziaException.class, () -> {
            agenciaService.getAllNames();
        });
    }
}