package com.example.democampanha.services;

import com.example.democampanha.repositories.ClienteRepository;
import com.example.democampanha.models.Cliente;
import com.example.democampanha.services.exceptions.DatabaseException;
import com.example.democampanha.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public void delete(Long id){
        try {
            clienteRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Cliente update(Long id, Cliente cliente){
        try{
            Cliente obj = clienteRepository.getOne(id);
            updateData(obj, cliente);
            return clienteRepository.save(obj);
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Cliente obj, Cliente cliente) {
        obj.setNomeCompleto(cliente.getNomeCompleto());
        obj.setEmail(cliente.getEmail());
        obj.setDataNascimento(cliente.getDataNascimento());
        obj.setIdMeuTimeCoracao(cliente.getIdMeuTimeCoracao());
    }
}
