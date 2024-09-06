package com.bank.bank.service.impl;


import com.bank.bank.dto.AgenciaRequestDTO;

import com.bank.bank.exceptions.ArgumentoMissedException;
import com.bank.bank.exceptions.ListaVaziaException;
import com.bank.bank.models.Agencias;
import com.bank.bank.repository.AgenciasDAO;
import com.bank.bank.service.AgenciaService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AgenciasServiceImpl implements AgenciaService {

    @Autowired //
    private AgenciasDAO agenciasRepository;


    private static final Map<String, String> estadoParaCidade = new HashMap<>();

    static {
        estadoParaCidade.put("São Paulo", "São Paulo");
        estadoParaCidade.put("Rio de Janeiro", "Rio de Janeiro");
        estadoParaCidade.put("Acre", "Rio Branco");
        estadoParaCidade.put("Alagoas", "Maceió");
        estadoParaCidade.put("Amapá", "Macapá");
        estadoParaCidade.put("Amazonas", "Manaus");
        estadoParaCidade.put("Bahia", "Salvador");
        estadoParaCidade.put("Ceará", "Fortaleza");
        estadoParaCidade.put("Distrito Federal", "Brasília");
        estadoParaCidade.put("Espírito Santo", "Vitória");
        estadoParaCidade.put("Goiás", "Goiânia");
        estadoParaCidade.put("Maranhão", "São Luís");
        estadoParaCidade.put("Mato Grosso", "Cuiabá");
        estadoParaCidade.put("Mato Grosso do Sul", "Campo Grande");
        estadoParaCidade.put("Minas Gerais", "Belo Horizonte");
        estadoParaCidade.put("Pará", "Belém");
        estadoParaCidade.put("Paraíba", "João Pessoa");
        estadoParaCidade.put("Paraná", "Curitiba");
        estadoParaCidade.put("Pernambuco", "Recife");
        estadoParaCidade.put("Piauí", "Teresina");
        estadoParaCidade.put("Rio Grande do Norte", "Natal");
        estadoParaCidade.put("Rio Grande do Sul", "Porto Alegre");
        estadoParaCidade.put("Rondônia", "Porto Velho");
        estadoParaCidade.put("Roraima", "Boa Vista");
        estadoParaCidade.put("Santa Catarina", "Florianópolis");
        estadoParaCidade.put("Sergipe", "Aracaju");
        estadoParaCidade.put("Tocantins", "Palmas");
    }

    @Override
    public void add(Agencias agencias) {
        String estado = agencias.getEstado();

        if (estado.isEmpty()) {
            throw new ArgumentoMissedException();
        }

        String cidade = estadoParaCidade.get(estado);

        if (cidade == null) {
            throw new IllegalArgumentException("Estado não reconhecido: " + estado);
        }

        agencias.setNomeAgencia(cidade);
        agenciasRepository.save(agencias);
    }



    @Override
    public List<Agencias> findAll() {
        return agenciasRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        agenciasRepository.deleteById(id);
    }


    @Override
    @Transactional // Como tem mais de uma ida a base de dados precisa de um transactional
    public void update(Long id, AgenciaRequestDTO agenciaRequestDTO) {
        Agencias agenciasGet = agenciasRepository.findById(id).get();
        if (agenciasGet != null) {
            agenciasGet.setEstado(agenciaRequestDTO.estado());
            agenciasGet.setNomeAgencia(agenciaRequestDTO.nomeAgencia());

            agenciasRepository.save(agenciasGet);
        }
    }

    @Override
    public Agencias getById(Long id) {
       return agenciasRepository.findById(id).get();
    }

    @Override
    public List<String> getAllNames() {
        if (agenciasRepository.getAllNames().isEmpty()){
            throw new ListaVaziaException();
        }
        return agenciasRepository.getAllNames();
    }


}


