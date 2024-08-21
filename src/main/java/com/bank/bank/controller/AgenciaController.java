package com.bank.bank.controller;

import com.bank.bank.dto.AgenciaRequestDTO;
import com.bank.bank.dto.AgenciaResponseDTO;
import com.bank.bank.models.Agencias;
import com.bank.bank.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agencia")
public class AgenciaController {


    @Autowired
    private AgenciaService agenciaService;

    //TODO ESTUDA RESPOSTA DO SISTEMA

    // PATH VARIABLE: Valor passado na url(uri)
    // REQUEST BODY: Valor passado no body, corpo da requisição
    // REQUEST PARAM: Valor passado

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Agencias agencias) {

        agenciaService.add(agencias);
        return ResponseEntity.status(201).build(); //
    }//

    @GetMapping("/getAll")
    public ResponseEntity<List<Agencias>> getAll() {
        List<Agencias> agenciasList = agenciaService.findAll();
        return ResponseEntity.ok(agenciasList);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Agencias> update(@PathVariable Long id, @RequestBody AgenciaRequestDTO agenciaRequestDTO) {
        agenciaService.update(id, agenciaRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        agenciaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agencias> getById(@PathVariable Long id) {
        Agencias getAgencias = agenciaService.getById(id);
        return ResponseEntity.ok(getAgencias);
    }

    @GetMapping("/getAllNames")
    public ResponseEntity<List<String>> getAllNames() {
        List<String> nomes = agenciaService.getAllNames();
        return ResponseEntity.ok(nomes);
    }

}
