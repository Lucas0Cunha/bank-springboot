package com.bank.bank.controller;

import com.bank.bank.dto.ClienteRequestDTO;
import com.bank.bank.models.Cliente;
import com.bank.bank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    //TODO ESTUDA RESPOSTA DO SISTEMA

    // PATH VARIABLE: Valor passado na url(uri)
    // REQUEST BODY: Valor passado no body, corpo da requisição
    // REQUEST PARAM: Valor passado

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Cliente cliente) {
        // O requestBody indica que a inserção do parametro deve ser realiza no body da requisição
        // Tbm é legal saber que o Spring converte automaticamente no corpo da requisição pelo user de um JSON para um obj Java
        clienteService.add(cliente);
        return ResponseEntity.status(201).build(); //
    }//

    @GetMapping("/getAll")
    public ResponseEntity<List<Cliente>> getAll() {
        // ResponseEntity: Classe já existente que facilita a resposta com o APIRest, me fornecendo metodos para resposta especifica fornecida pelo response, ex:ok...

        List<Cliente> clienteList = clienteService.findAll();
        return ResponseEntity.ok(clienteList);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        clienteService.update(id, clienteRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        Cliente getCliente = clienteService.getById(id);
        return ResponseEntity.ok(getCliente);
    }

    @GetMapping("/getAllNames")
    public ResponseEntity<List<String>> getAllNames() {
        List<String> nomes = clienteService.getAllNames();
        return ResponseEntity.ok(nomes);
    }

}
