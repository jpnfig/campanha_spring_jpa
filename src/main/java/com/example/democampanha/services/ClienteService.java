package com.example.democampanha.services;

import com.example.democampanha.repositories.ClienteRepository;
import com.example.democampanha.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){return clienteRepository.findAll();}

    public Cliente findById(Long id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow();
    }
    public Cliente insert(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void delete(Long id){  clienteRepository.deleteById(id);
    }

    public Cliente update(Long id, Cliente cliente){
         Cliente obj = clienteRepository.getOne(id);
        return clienteRepository.save(obj);
    }

}
