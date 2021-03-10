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
import java.util.NoSuchElementException;
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
        try{
            Torcedor torcedor = TorcedorRepository.findById(id).get();
            TorcedorResponse TorcedorResponse = mapperTorcedorToTorcedorResponse.toResponse(torcedor);
            return TorcedorResponse;
        }catch(NoSuchElementException e) {
            throw new ResourceNotFoundException(id);
        }

    }

    public TorcedorResponse salvar(TorcedorRequest torcedorRequest){

        Torcedor torcedor = mapperTorcedorRequestToTorcedor.toEntity(torcedorRequest);
        List<Torcedor> listaTorcedores = TorcedorRepository.findAll();

        List<Torcedor> procuraTorcedor =
                listaTorcedores.
                        stream().
                        filter(p -> p.getEmail().equals(torcedor.getEmail())).
                        collect(Collectors.toList());

        if (procuraTorcedor.isEmpty()){
            TorcedorRepository.save(torcedor);
        }else{
            throw new TorcedorAlreadyExistsException(torcedor.getEmail());
        }

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
            Torcedor torcedor = TorcedorRepository.getOne(id);
            torcedor = mapperTorcedorRequestToTorcedor.toEntity(torcedorRequest);
            torcedor.setIdTorcedor(id);
            TorcedorRepository.save(torcedor);
            TorcedorResponse torcedorResponse = new TorcedorResponse();
            torcedorResponse = mapperTorcedorToTorcedorResponse.toResponse(torcedor);
            return torcedorResponse;
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

}
