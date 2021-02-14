package com.example.democampanha.services;

import com.example.democampanha.dto.CampanhaRequest;
import com.example.democampanha.dto.CampanhaResponse;
import com.example.democampanha.models.Campanha;
import com.example.democampanha.repositories.CampanhaRepository;
import com.example.democampanha.services.exceptions.DatabaseException;
import com.example.democampanha.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    public List<CampanhaResponse> buscarTodos(){
        Campanha campanha = new Campanha();
        CampanhaResponse campanhaResponse = new CampanhaResponse();
        List<CampanhaResponse> campanhaResponseLista = new ArrayList<>();
        List<Campanha> campanhas = campanhaRepository.findAll();

        for (Campanha lista : campanhas){
            if (lista.getDataFimVigencia().isAfter(LocalDate.now()) ||
                lista.getDataFimVigencia().isEqual(LocalDate.now())) {
                campanhaResponse.setId(lista.getId());
                campanhaResponse.setNome(lista.getNome());
                campanhaResponse.setDataInicioVigencia(lista.getDataInicioVigencia());
                campanhaResponse.setDataFimVigencia(lista.getDataFimVigencia());
                campanhaResponse.setTimeCoracao(lista.getTimeCoracao());
                campanhaResponseLista.add(campanhaResponse);
            }
        }

        return campanhaResponseLista;
    }

    public CampanhaResponse buscarPorId(Long id){
        CampanhaResponse campanhaResponse = new CampanhaResponse();
        Optional<Campanha> campanha = campanhaRepository.findById(id);
        campanhaResponse.setId(campanha.get().getId());
        campanhaResponse.setNome(campanha.get().getNome());
        campanhaResponse.setDataInicioVigencia(campanha.get().getDataInicioVigencia());
        campanhaResponse.setDataFimVigencia(campanha.get().getDataFimVigencia());
        campanhaResponse.setTimeCoracao(campanha.get().getTimeCoracao());
        return campanhaResponse;
    }

    public CampanhaResponse salvar(CampanhaRequest campanhaRequest){
        Campanha campanha = new Campanha();
        CampanhaResponse campanhaResponse = new CampanhaResponse();
        campanha.setNome(campanhaRequest.getNome());
        campanha.setDataInicioVigencia(campanhaRequest.getDataInicioVigencia());
        campanha.setDataFimVigencia(campanha.getDataFimVigencia());
        campanha.setTimeCoracao(campanhaRequest.getTimeCoracao());
        verificaDataCampanhasExistentes(campanha);
        campanhaRepository.save(campanha);
        campanhaResponse.setId(campanha.getId());
        campanhaResponse.setNome(campanha.getNome());
        campanhaResponse.setDataInicioVigencia(campanha.getDataInicioVigencia());
        campanhaResponse.setDataFimVigencia(campanha.getDataFimVigencia());
        campanhaResponse.setTimeCoracao(campanha.getTimeCoracao());
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
            atualizarDadosCampanha(campanhaRequest, campanha);
            CampanhaResponse campanhaResponse = new CampanhaResponse();
            campanhaRepository.save(campanha);
            campanhaResponse.setId(campanha.getId());
            campanhaResponse.setNome(campanha.getNome());
            campanhaResponse.setTimeCoracao(campanha.getTimeCoracao());
            campanhaResponse.setDataInicioVigencia(campanha.getDataInicioVigencia());
            campanhaResponse.setDataFimVigencia(campanha.getDataFimVigencia());
            return campanhaResponse;
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void atualizarDadosCampanha(CampanhaRequest campanhaRequest, Campanha campanha) {
        campanha.setNome(campanhaRequest.getNome());
        campanha.setDataInicioVigencia(campanhaRequest.getDataInicioVigencia());
        campanha.setDataFimVigencia(campanhaRequest.getDataFimVigencia());
        campanha.setTimeCoracao(campanhaRequest.getTimeCoracao());
    }

    private void verificaDataCampanhasExistentes(Campanha campanha) {
        List<Campanha> listAux = campanhaRepository.findAll();
        for(Campanha camp : listAux){
            if (camp.getDataFimVigencia().isEqual(campanha.getDataFimVigencia())) {
                LocalDate dataAuxiliar = camp.getDataFimVigencia();
                camp.setDataFimVigencia(dataAuxiliar.plusDays(1));
                campanhaRepository.save(camp);
            }
        }
    }

}
