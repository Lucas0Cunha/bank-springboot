package com.bank.bank.controller;

import com.bank.bank.dto.ClienteRequestDTO;
import com.bank.bank.dto.ClienteRequestDTOCredito;
import com.bank.bank.dto.ClienteRequestDTOLocal;
import com.bank.bank.dto.ClienteResponseDTOContas;
import com.bank.bank.models.Cliente;
import com.bank.bank.models.Contas;
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


    @GetMapping("/getInfoCliente/{id}")
    public ResponseEntity<List<Contas>> getInfo(@PathVariable Long id) {
        List<Contas> infos = clienteService.getInfoCliente(id);
        return ResponseEntity.ok(infos);
    }


    @GetMapping("/getMontante/{id}")
    public ResponseEntity<List<String>> getSaldoCliente(@PathVariable Long id) {
        List<String> montantes = clienteService.getSaldoCliente(id);
        return ResponseEntity.ok(montantes);
    }

    @GetMapping("/getInfos/{id}")
    public ResponseEntity<List<ClienteResponseDTOContas>> getSaldoClienteConta(@PathVariable Long id) {
        List<ClienteResponseDTOContas> infos = clienteService.getInfoClienteConta(id);
        return ResponseEntity.ok(infos);
    }

    @GetMapping("/getInfoCredito")
    public ResponseEntity<List<ClienteRequestDTOCredito>> getInfoClienteCredito() {
        List<ClienteRequestDTOCredito> infos = clienteService.getInfoClienteCredito();
        return ResponseEntity.ok(infos);
    }

    @GetMapping("/getInfoClienteLocal")
    public ResponseEntity<List<ClienteRequestDTOLocal>> getInfoClienteLocal(){
        List<ClienteRequestDTOLocal> infos = clienteService.getInfoClienteLocal();
        return ResponseEntity.ok(infos);
    }

}
