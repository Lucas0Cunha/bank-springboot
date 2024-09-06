package com.bank.bank.service.impl;

import com.bank.bank.dto.ClienteRequestDTOCredito;
import com.bank.bank.dto.ClienteRequestDTOLocal;
import com.bank.bank.dto.ClienteResponseDTOContas;
import com.bank.bank.exceptions.ListaVaziaException;
import com.bank.bank.repository.ClienteDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClienteServiceImplTest {

    @Mock
    ClienteDAO clienteRepository;

    @InjectMocks
    ClienteServiceImpl clienteService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void getInfoClienteConta() {
        List<Object[]> infoclienteconta = new ArrayList<>();
        Object[] obj1 = {"Lucas", "São Paulo","SP"};
        infoclienteconta.add(obj1);

        when(clienteRepository.getInfoClienteConta(1L)).thenReturn(infoclienteconta);

        List<ClienteResponseDTOContas> resultado = clienteService.getInfoClienteConta(1L);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Lucas", resultado.get(0).nome());
        assertEquals("São Paulo", resultado.get(0).nomeAgencia());
        assertEquals("SP", resultado.get(0).estado());
    }


    //TODO ESTA CERTO ESSE METODO........
    @Test
    void getInfoClienteCredito() {
        List<Object[]> infoclientecredito = new ArrayList<>();
        Object[] obj1 = {"Lucas","tipo"};
        infoclientecredito.add(obj1);

        when(clienteRepository.getInfoClienteCredito()).thenReturn(infoclientecredito);

        List<ClienteRequestDTOCredito> resultado = clienteService.getInfoClienteCredito();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Lucas", resultado.get(0).nome());
        assertEquals("tipo", resultado.get(0).tipoConta());

    }

    @Test
    void getInfoClienteLocal() {
        List<Object[]> infoclientelocal = new ArrayList<>();
        Object[] obj1 = {"Lucas", "São Paulo","São Paulo", "SP"};
        infoclientelocal.add(obj1);

        when(clienteRepository.getInfoClienteLocal()).thenReturn(infoclientelocal);

        List<ClienteRequestDTOLocal> resultado = clienteService.getInfoClienteLocal();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Lucas", resultado.get(0).nome());
        assertEquals("São Paulo", resultado.get(0).estadoCliente());
        assertEquals("São Paulo", resultado.get(0).estadoAgencia());
        assertEquals("SP", resultado.get(0).nomeAgencia());
    }

    @Test
    void getAllNamesReturnsListOfNames() {
        // Configura o mock para retornar uma lista simulada de nomes
        List<String> nomesSimulados = List.of("Cliente 1", "Cliente 2");
        when(clienteRepository.getAllNames()).thenReturn(nomesSimulados);

        // Executa o método a ser testado
        List<String> resultado = clienteService.getAllNames();

        // Verifica se o resultado está correto
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Cliente 1", resultado.get(0));
        assertEquals("Cliente 2", resultado.get(1));
    }

    @Test
    void getAllNamesThrowsExceptionWhenEmpty() {
        // Configura o mock para retornar uma lista vazia
        when(clienteRepository.getAllNames()).thenReturn(List.of());

        // Verifica se a exceção é lançada
        assertThrows(ListaVaziaException.class, () -> clienteService.getAllNames());
    }
}
