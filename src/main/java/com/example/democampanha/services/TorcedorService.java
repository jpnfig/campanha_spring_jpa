package com.example.democampanha.services;

import com.example.democampanha.dto.TorcedorRequest;
import com.example.democampanha.dto.TorcedorResponse;
import com.example.democampanha.mappers.MapperTorcedorRequestToTorcedor;
import com.example.democampanha.mappers.MapperTorcedorToTorcedorResponse;
import com.example.democampanha.repositories.TorcedorRepository;
import com.example.democampanha.models.Torcedor;
import com.example.democampanha.services.exceptions.TorcedorAlreadyExistsException;
import com.example.democampanha.services.exceptions.DatabaseException;
import com.example.democampanha.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TorcedorService {

    private final TorcedorRepository TorcedorRepository;
    private final MapperTorcedorRequestToTorcedor mapperTorcedorRequestToTorcedor;
    private final MapperTorcedorToTorcedorResponse mapperTorcedorToTorcedorResponse;

    public List<TorcedorResponse> buscarTodos(){
        List<Torcedor> listaTorcedores = TorcedorRepository.findAll();
        List<TorcedorResponse> torcedoresResponses =
                listaTorcedores
                .stream()
                .map(mapperTorcedorToTorcedorResponse::toResponse)
                .collect(Collectors.toList());
        return torcedoresResponses;
    }

    public TorcedorResponse buscarPorId(Long id){
        Torcedor torcedor = TorcedorRepository.findById(id).get();
        TorcedorResponse TorcedorResponse = mapperTorcedorToTorcedorResponse.toResponse(torcedor);
        return TorcedorResponse;
    }

    public TorcedorResponse salvar(TorcedorRequest torcedorRequest){
        Torcedor torcedor = mapperTorcedorRequestToTorcedor.toEntity(torcedorRequest);
        validaExisteCadastroTorcedor(torcedor);
        TorcedorRepository.save(torcedor);
        TorcedorResponse torcedorResponse = mapperTorcedorToTorcedorResponse.toResponse(torcedor);
        return torcedorResponse;
    }

    public void apagar(Long id){
        try {
            TorcedorRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public TorcedorResponse atualizar(Long id, TorcedorRequest torcedorRequest){
        try{
            Torcedor torcedor = new Torcedor();
            torcedor = TorcedorRepository.getOne(id);
            torcedor = mapperTorcedorRequestToTorcedor.toEntity(torcedorRequest);
            TorcedorRepository.save(torcedor);
            TorcedorResponse torcedorResponse = new TorcedorResponse();
            torcedorResponse = mapperTorcedorToTorcedorResponse.toResponse(torcedor);
            return torcedorResponse;
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void validaExisteCadastroTorcedor(Torcedor torcedor){
        List<Torcedor> listaTorcedores = TorcedorRepository.findAll();
        for(Torcedor torcedores: listaTorcedores){
            if (torcedores.getEmail().equals(torcedor.getEmail())){
                throw new TorcedorAlreadyExistsException(torcedores.getIdTorcedor());
            }
        }
    }

}
