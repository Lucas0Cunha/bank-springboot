package com.bank.bank.service.impl;

import com.bank.bank.dto.ContaResponseAgenciaDTO;
import com.bank.bank.dto.ContaResponseDTO;
import com.bank.bank.dto.ContaTestDTO;
import com.bank.bank.models.Agencias;
import com.bank.bank.models.Cliente;
import com.bank.bank.models.ContaSalario;
import com.bank.bank.models.Contas;
import com.bank.bank.repository.ContaDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContaServiceImplTest {

    @Mock
    ContaDAO contaRepository;

    @InjectMocks
    ContaServiceImpl contaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void getAllContas() {

        List<String> list = List.of("Lucas","111","tipo");

        when(contaRepository.getAllContas()).thenReturn(list);

        List<String> resultado = contaService.getAllContas();

        // Verifica se o resultado está correto
        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals("Lucas", resultado.get(0));
        assertEquals("111", resultado.get(1));
        assertEquals("tipo", resultado.get(2));


        verify(contaRepository, times(1)).getAllContas();
    }

    @Test
    void getContaValue() {
        List<Object[]> contaValue = new ArrayList<>();
        Object[] obj1 = {"Lucas",123};
        contaValue.add(obj1);

        when(contaRepository.getContaValue(1L)).thenReturn(contaValue);

        ContaResponseDTO resultado = contaService.getContaValue(1L);

        assertNotNull(resultado);
        //TODO TIRAR DUVIDA
        //assertEquals(1, resultado);
        assertEquals("Lucas", resultado.nome());
        assertEquals(123, resultado.saldo());
    }

    @Test
    void getContaValueAll() {
        List<Object[]> contaValueAll = new ArrayList<>();
        Object[] obj1 = {"Lucas",123};
        contaValueAll.add(obj1);

        when(contaRepository.getContaValueAll()).thenReturn(contaValueAll);

        List<ContaResponseDTO> resultado = contaService.getContaValueAll();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Lucas", resultado.get(0).nome());
        assertEquals(123, resultado.get(0).saldo());
    }

    @Test
    void getContasByAgencia() {
        List<Object[]> contaByAgencia = new ArrayList<>();
        Object[] obj1 = {1L, 123, 1L, "tipoConta", "nomeAgencia"};
        contaByAgencia.add(obj1);

        when(contaRepository.getContasByAgencia(1L)).thenReturn(contaByAgencia);

        List<ContaResponseAgenciaDTO> resultado = contaService.getContasByAgencia(1L);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).idCliente());
        assertEquals(123, resultado.get(0).numero());
        assertEquals("nomeAgencia", resultado.get(0).nomeAgencia());
    }


    @Test
    void getContaType() {
        Cliente cliente = new Cliente();
        Agencias agencias = new Agencias();

        List<Contas> list = new ArrayList<>();
        list.add(new ContaSalario("311",cliente,13,agencias));


        when(contaRepository.getContaType(1L)).thenReturn(list);

        List<ContaTestDTO> resultado = contaService.getContaType(1L);

        // Verifica se o resultado está correto
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(list.get(0).getSaldo(), resultado.get(0).saldo());
        assertEquals(list.get(0).getCliente().getId(), resultado.get(0).id());
        assertEquals(list.get(0).getSaldo(), resultado.get(0).saldo());
        assertEquals(list.get(0).getNumero(), resultado.get(0).numero());

        verify(contaRepository, times(1)).getContaType(1L);
    }
}