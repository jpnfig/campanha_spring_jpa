package com.example.democampanha.resources;

import ch.qos.logback.core.net.server.Client;
import com.example.democampanha.dto.CampanhaResponse;
import com.example.democampanha.dto.ClienteRequest;
import com.example.democampanha.dto.ClienteResponse;
import com.example.democampanha.models.Cliente;
import com.example.democampanha.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> list = clienteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Cliente obj = clienteService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody ClienteRequest obj){
        List<Cliente> list = clienteService.findAll();
        boolean resp = validaExisteCadastroCliente(list, obj.transformaClienteRequestParaCliente());
        if (resp){
            return ResponseEntity.ok().build();
        }else{
            Cliente cliente = clienteService.insert(obj.transformaClienteRequestParaCliente());
            return new ResponseEntity(ClienteResponse.transformaClienteEmClienteResponse(cliente), HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody ClienteRequest obj){
        Cliente cliente = clienteService.update(id, obj.transformaClienteRequestParaCliente());
        return new ResponseEntity(ClienteResponse.transformaClienteEmClienteResponse(cliente), HttpStatus.OK);
    }

    private boolean validaExisteCadastroCliente(List<Cliente> list, Cliente obj){
        for(Cliente cliente : list){
            if (cliente.getEmail().equals(obj.getEmail())){
                System.out.println("Cliente com cadastro já efetuado!");
                return true;
            }
        }
        return false;
    }

}
