package com.bank.bank.controller;

import com.bank.bank.dto.ContaRequestDTO;
import com.bank.bank.dto.ContaResponseDTO;
import com.bank.bank.models.Contas;
import com.bank.bank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;


    @PostMapping //
    public ResponseEntity<String> add(@RequestBody ContaRequestDTO contaRequestDTO) {
        // O requestBody indica que a inserção do parametro deve ser realiza no body da requisição
        // Tbm é legal saber que o Spring converte automaticamente no corpo da requisição pelo user de um JSON para um obj Java
        contaService.add(contaRequestDTO);
        return ResponseEntity.status(201).build(); //
    }


    //
    @GetMapping("/getAll")
    public ResponseEntity<List<ContaResponseDTO>> getAll() {
        // ResponseEntity: Classe já existente que facilita a resposta com o APIRest, me fornecendo metodos para resposta especifica fornecida pelo response, ex:ok...

        List<ContaResponseDTO> contasList = contaService.findAll();
        return ResponseEntity.ok(contasList);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Contas> update(@PathVariable Long id, @RequestBody ContaRequestDTO contaRequestDTO) {
        contaService.update(id, contaRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        contaService.delete(id);
        return ResponseEntity.ok().build();
    }


    //
    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> getById(@PathVariable Long id) {
        ContaResponseDTO getConta = contaService.getById(id);
        if (getConta != null) {
            return ResponseEntity.ok(getConta);
        }
        return ResponseEntity.status(404).build();
    }
}







