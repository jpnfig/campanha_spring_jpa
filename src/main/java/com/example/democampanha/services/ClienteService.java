package com.example.democampanha.services;

import com.example.democampanha.dto.ClienteRequest;
import com.example.democampanha.dto.ClienteResponse;
import com.example.democampanha.repositories.ClienteRepository;
import com.example.democampanha.models.Cliente;
import com.example.democampanha.services.exceptions.DatabaseException;
import com.example.democampanha.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteResponse> buscarTodos(){
        List<Cliente> clientes = clienteRepository.findAll();
        ClienteResponse clienteResponse = new ClienteResponse();
        List<ClienteResponse> clienteResponseLista = new ArrayList<>();

        for (Cliente lista : clientes){
            clienteResponse.setId(lista.getId());
            clienteResponse.setEmail(lista.getEmail());
            clienteResponse.setDataNascimento(lista.getDataNascimento());
            clienteResponse.setNomeCompleto(lista.getNomeCompleto());
            clienteResponse.setTimeCoracao(lista.getTimeCoracao());
            clienteResponseLista.add(clienteResponse);
        }

        return clienteResponseLista;
    }

    public ClienteResponse buscarPorId(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(cliente.get().getId());
        clienteResponse.setEmail(cliente.get().getEmail());
        clienteResponse.setDataNascimento(cliente.get().getDataNascimento());
        clienteResponse.setNomeCompleto(cliente.get().getNomeCompleto());
        clienteResponse.setTimeCoracao(cliente.get().getTimeCoracao());
        return clienteResponse;
    }
    public ClienteResponse salvar(ClienteRequest clienteRequest){
        Cliente cliente = new Cliente();
        ClienteResponse clienteResponse = new ClienteResponse();
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setNomeCompleto(clienteRequest.getNomeCompleto());
        cliente.setDataNascimento(clienteRequest.getDataNascimento());
        cliente.setTimeCoracao(clienteRequest.getTimeCoracao());
        boolean resp = validaExisteCadastroCliente(cliente);
        if (resp){
            clienteResponse = null;
        }else{
            clienteRepository.save(cliente);
            clienteResponse.setId(cliente.getId());
            clienteResponse.setNomeCompleto(cliente.getNomeCompleto());
            clienteResponse.setEmail(cliente.getEmail());
            clienteResponse.setDataNascimento(cliente.getDataNascimento());
            clienteResponse.setTimeCoracao(cliente.getTimeCoracao());
        }
        return clienteResponse;
    }

    public void apagar(Long id){
        try {
            clienteRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ClienteResponse atualizar(Long id, ClienteRequest clienteRequest){
        try{
            Cliente cliente = new Cliente();
            ClienteResponse clienteResponse = new ClienteResponse();
            cliente = clienteRepository.getOne(id);
            atualizarDados(cliente, clienteRequest);
            clienteRepository.save(cliente);
            clienteResponse.setId((cliente.getId()));
            clienteResponse.setNomeCompleto(cliente.getNomeCompleto());
            clienteResponse.setEmail(cliente.getEmail());
            clienteResponse.setDataNascimento(cliente.getDataNascimento());
            clienteResponse.setTimeCoracao(cliente.getTimeCoracao());
            return clienteResponse;
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void atualizarDados(Cliente cliente, ClienteRequest clienteRequest) {
        cliente.setNomeCompleto(clienteRequest.getNomeCompleto());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setDataNascimento(clienteRequest.getDataNascimento());
        cliente.setTimeCoracao(clienteRequest.getTimeCoracao());
    }

    private boolean validaExisteCadastroCliente(Cliente cliente){
        List<Cliente> clientes = clienteRepository.findAll();
        for(Cliente lista : clientes){
            if (lista.getEmail().equals(cliente.getEmail())){
                System.out.println("Cliente com cadastro já efetuado!");
                return true;
            }
        }
        return false;
    }

}
