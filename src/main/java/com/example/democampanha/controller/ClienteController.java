package com.example.democampanha.controller;

import com.example.democampanha.dto.ClienteRequest;
import com.example.democampanha.dto.ClienteResponse;
import com.example.democampanha.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> buscarTodos(){
        List<ClienteResponse> list = clienteService.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable Long id){
        ClienteResponse clienteResponse = clienteService.buscarPorId(id);
        return ResponseEntity.ok().body(clienteResponse);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> salvar(@RequestBody ClienteRequest clienteRequest){
        ClienteResponse clienteResponse = clienteService.salvar(clienteRequest);
        return ResponseEntity.ok(clienteResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id){
        clienteService.apagar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteResponse> atualizar(
            @PathVariable Long id,
            @RequestBody ClienteRequest clienteRequest){
        ClienteResponse clienteResponse = clienteService.atualizar(id, clienteRequest);
        return ResponseEntity.ok(clienteResponse);
    }

}
