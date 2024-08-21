package com.bank.bank.service.impl;


import com.bank.bank.dto.AgenciaRequestDTO;
import com.bank.bank.dto.AgenciaResponseDTO;
import com.bank.bank.models.Agencias;
import com.bank.bank.repository.AgenciasDAO;
import com.bank.bank.service.AgenciaService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenciasServiceImpl implements AgenciaService {

    @Autowired //
    private AgenciasDAO agenciasRepository;

    @Override
    public void add(Agencias agencias) {


        String estado = agencias.getEstado();

        if (estado.equalsIgnoreCase("São Paulo")) {
            agencias.setNomeAgencia("São Paulo");

        } else if (estado.equalsIgnoreCase("Rio de Janeiro")) {
            agencias.setNomeAgencia("Rio de Janeiro");
        } else if (estado.equalsIgnoreCase("Acre")) {
            agencias.setNomeAgencia("Rio Branco");
        } else if (estado.equalsIgnoreCase("Alagoas")) {
            agencias.setNomeAgencia("Maceió");
        } else if (estado.equalsIgnoreCase("Amapá")) {
            agencias.setNomeAgencia("Macapá");
        } else if (estado.equalsIgnoreCase("Amazonas")) {
            agencias.setNomeAgencia("Manaus");
        } else if (estado.equalsIgnoreCase("Bahia")) {
            agencias.setNomeAgencia("Salvador");
        } else if (estado.equalsIgnoreCase("Ceará")) {
            agencias.setNomeAgencia("Fortaleza");
        } else if (estado.equalsIgnoreCase("Distrito Federal")) {
            agencias.setNomeAgencia("Brasília");
        } else if (estado.equalsIgnoreCase("Espírito Santo")) {
            agencias.setNomeAgencia("Vitória");
        } else if (estado.equalsIgnoreCase("Goiás")) {
            agencias.setNomeAgencia("Goiânia");
        } else if (estado.equalsIgnoreCase("Maranhão")) {
            agencias.setNomeAgencia("São Luís");
        } else if (estado.equalsIgnoreCase("Mato Grosso")) {
            agencias.setNomeAgencia("Cuiabá");
        } else if (estado.equalsIgnoreCase("Mato Grosso do Sul")) {
            agencias.setNomeAgencia("Campo Grande");
        } else if (estado.equalsIgnoreCase("Minas Gerais")) {
            agencias.setNomeAgencia("Belo Horizonte");
        } else if (estado.equalsIgnoreCase("Pará")) {
            agencias.setNomeAgencia("Belém");
        } else if (estado.equalsIgnoreCase("Paraíba")) {
            agencias.setNomeAgencia("João Pessoa");
        } else if (estado.equalsIgnoreCase("Paraná")) {
            agencias.setNomeAgencia("Curitiba");
        } else if (estado.equalsIgnoreCase("Pernambuco")) {
            agencias.setNomeAgencia("Recife");
        } else if (estado.equalsIgnoreCase("Piauí")) {
            agencias.setNomeAgencia("Teresina");
        } else if (estado.equalsIgnoreCase("Rio Grande do Norte")) {
            agencias.setNomeAgencia("Natal");
        } else if (estado.equalsIgnoreCase("Rio Grande do Sul")) {
            agencias.setNomeAgencia("Porto Alegre");
        } else if (estado.equalsIgnoreCase("Rondônia")) {
            agencias.setNomeAgencia("Porto Velho");
        } else if (estado.equalsIgnoreCase("Roraima")) {
            agencias.setNomeAgencia("Boa Vista");
        } else if (estado.equalsIgnoreCase("Santa Catarina")) {
            agencias.setNomeAgencia("Florianópolis");
        } else if (estado.equalsIgnoreCase("Sergipe")) {
            agencias.setNomeAgencia("Aracaju");
        } else if (estado.equalsIgnoreCase("Tocantins")) {
            agencias.setNomeAgencia("Palmas");
        }

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
        return agenciasRepository.getAll();
    }


}


