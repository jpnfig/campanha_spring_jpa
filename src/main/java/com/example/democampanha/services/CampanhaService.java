package com.example.democampanha.services;

import com.example.democampanha.models.Campanha;
import com.example.democampanha.repositories.CampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        verificaDataFimVigencia(campanha.getDataFimVigencia());
        return campanhaRepository.save(campanha);
    }

    public void delete(Long id){
        campanhaRepository.deleteById(id);
    }

    public Campanha update(Long id, Campanha campanha){
        Campanha obj = campanhaRepository.getOne(id);
        return campanhaRepository.save(obj);
    }

    public void verificaDataFimVigencia(LocalDate dataFimVigencia) {
        List<Campanha> campanhas = new ArrayList<>();
        campanhas = campanhaRepository.findAll();

        for (Campanha camp : campanhas) {
            if (camp.getDataFimVigencia().isEqual(dataFimVigencia)) {
                LocalDate dataAuxiliar = camp.getDataFimVigencia();
                dataAuxiliar = dataAuxiliar.plusDays(1);
                camp.setDataFimVigencia(dataAuxiliar);
                campanhaRepository.save(camp);
            }
        }
    }

}
