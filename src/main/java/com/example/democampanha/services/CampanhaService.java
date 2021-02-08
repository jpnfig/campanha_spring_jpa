package com.example.democampanha.services;

import com.example.democampanha.models.Campanha;
import com.example.democampanha.models.Cliente;
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

    public List<Campanha> findAll(){
        return campanhaRepository.findAll();
    }

    public Campanha findById(Long id){
        Optional<Campanha> obj = campanhaRepository.findById(id);
        return obj.orElseThrow();
    }

    public Campanha insert(Campanha campanha){
        return campanhaRepository.save(campanha);
    }

    public void delete(Long id){
        try{
            campanhaRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Campanha update(Long id, Campanha campanha){
        try{
            Campanha obj = campanhaRepository.getOne(id);
            updateData(obj, campanha);
            return campanhaRepository.save(obj);
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Campanha obj, Campanha campanha) {
        obj.setNome(campanha.getNome());
        obj.setIdTimeCoracao(campanha.getIdTimeCoracao());
        obj.setDataInicioVigencia(campanha.getDataInicioVigencia());
        obj.setDataFimVigencia(campanha.getDataFimVigencia());
    }

}
