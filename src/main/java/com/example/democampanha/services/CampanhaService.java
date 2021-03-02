package com.example.democampanha.services;

import com.example.democampanha.dto.CampanhaRequest;
import com.example.democampanha.dto.CampanhaResponse;
import com.example.democampanha.mappers.MapperCampanhaRequestToCampanha;
import com.example.democampanha.mappers.MapperCampanhaToCampanhaResponse;
import com.example.democampanha.models.Campanha;
import com.example.democampanha.models.Torcedor;
import com.example.democampanha.models.enums.TimeCoracao;
import com.example.democampanha.repositories.CampanhaRepository;
import com.example.democampanha.repositories.TorcedorRepository;
import com.example.democampanha.services.exceptions.DatabaseException;
import com.example.democampanha.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampanhaService {

    private final CampanhaRepository campanhaRepository;
    private final TorcedorRepository torcedorRepository;
    private final MapperCampanhaRequestToCampanha mapperCampanhaRequestToCampanha;
    private final MapperCampanhaToCampanhaResponse mapperCampanhaToCampanhaResponse;

    public List<CampanhaResponse> buscarTodos() {
        List<Campanha> listaCampanhas = campanhaRepository.findAll();
        listaCampanhas.removeIf(p -> p.getDataFimVigencia().isBefore(LocalDate.now()));

        List<CampanhaResponse> listaCampanhasResponses =
                listaCampanhas
                        .stream()
                        .map(mapperCampanhaToCampanhaResponse::toResponse)
                        .collect(Collectors.toList());
        return listaCampanhasResponses;
    }

    public CampanhaResponse buscarPorId(Long id){
        try{
            Campanha campanha = campanhaRepository.findById(id).get();
            CampanhaResponse campanhaResponse = mapperCampanhaToCampanhaResponse.toResponse(campanha);
            return campanhaResponse;
        }catch(NoSuchElementException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<CampanhaResponse> buscarPorTimeCoracao(TimeCoracao timeCoracao){
        try{
            List<Campanha> listaCampanhas = campanhaRepository.findAll();
            listaCampanhas.removeIf(p -> p.getDataFimVigencia().isBefore(LocalDate.now()));
            listaCampanhas.removeIf(p -> p.getTimeCoracao() != timeCoracao);
            List<CampanhaResponse> listaCampanhasResponses =
                    listaCampanhas
                            .stream()
                            .map(mapperCampanhaToCampanhaResponse::toResponse)
                            .collect(Collectors.toList());
            return listaCampanhasResponses;
        }catch(NoSuchElementException e) {
            throw new ResourceNotFoundException(timeCoracao);
        }
    }

    public CampanhaResponse salvar(CampanhaRequest campanhaRequest){
        Campanha campanha = mapperCampanhaRequestToCampanha.toEntity(campanhaRequest);
        validaDatasCampanhas(campanha.getDataFimVigencia());
        campanhaRepository.save(campanha);
        CampanhaResponse campanhaResponse = mapperCampanhaToCampanhaResponse.toResponse(campanha);
        return campanhaResponse;
    }

    public CampanhaResponse adicionarTorcedorACampanha(Long idCampanha,  Long idTorcedor){
        Torcedor torcedor= torcedorRepository.findById(idTorcedor).get();
        Campanha campanha = campanhaRepository.findById(idCampanha).get();
        campanha.getTorcedores().add(torcedor);
        campanhaRepository.save(campanha);
        CampanhaResponse campanhaResponse = mapperCampanhaToCampanhaResponse.toResponse(campanha);
        return campanhaResponse;
    }

    public void apagar(Long id){
       try{
           campanhaRepository.deleteById(id);
       }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
       }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
       }
    }

    public CampanhaResponse atualizar(Long id, CampanhaRequest campanhaRequest){
        try{
            Campanha campanha = campanhaRepository.getOne(id);
            campanha = mapperCampanhaRequestToCampanha.toEntity(campanhaRequest);
            validaDatasCampanhas(campanha.getDataFimVigencia());
            campanha.setIdCampanha(id);
            campanhaRepository.save(campanha);
            CampanhaResponse campanhaResponse = mapperCampanhaToCampanhaResponse.toResponse(campanha);
            return campanhaResponse;
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void validaDatasCampanhas(LocalDate dataFimVigencia) {
        int dias = 0;
        LocalDate dataAtual, dataAnterior = null;
        List<Campanha> listaCampanhas = campanhaRepository.findAll();
        for (Campanha campanhas : listaCampanhas){
            if (campanhas.getDataFimVigencia().isEqual(dataFimVigencia) ||
                campanhas.getDataFimVigencia().isAfter(dataFimVigencia)){
                dias += 1;
                dataAtual = campanhas.getDataFimVigencia().plusDays(dias);
                if (dias > 1) {
                    assert dataAnterior != null;
                    if (dataAtual.isEqual(dataAnterior)) {
                        dias += 1;
                        dataAtual = campanhas.getDataFimVigencia().plusDays(dias);
                    }
                }
                campanhas.setDataFimVigencia(dataAtual);
                campanhaRepository.save(campanhas);
                dataAnterior = dataAtual;
            }
        }
    }

}
