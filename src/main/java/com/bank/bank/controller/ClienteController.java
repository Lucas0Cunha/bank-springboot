package com.bank.bank.controller;

import com.bank.bank.models.Cliente;
import com.bank.bank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping //
    public ResponseEntity<String>add(@RequestBody Cliente cliente ) { //
        clienteService.add(cliente);
        return ResponseEntity.ok().build(); //
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clienteList = clienteService.findAll();
        return ResponseEntity.ok(clienteList);
    }


    @PutMapping
    public ResponseEntity<Cliente> update (@RequestBody Cliente cliente){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/delete")
    public  ResponseEntity<String> delete (@PathVariable String cpfCnpj){
        clienteService.delete(cpfCnpj);
        return ResponseEntity.ok().build();
    }






}
