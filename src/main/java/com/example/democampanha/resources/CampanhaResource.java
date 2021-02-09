package com.example.democampanha.resources;

import com.example.democampanha.dto.CampanhaRequest;
import com.example.democampanha.dto.CampanhaResponse;
import com.example.democampanha.models.Campanha;
import com.example.democampanha.models.Cliente;
import com.example.democampanha.services.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/campanhas")
public class CampanhaResource {

    @Autowired
    private CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<Campanha>> findAll(){
        List<Campanha> list = campanhaService.findAll();
        List<Campanha> listAux = new ArrayList<>();
        listAux = verificaCampanhasVigentes(list);
        return ResponseEntity.ok().body(listAux);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Campanha> findById(@PathVariable Long id){
        Campanha obj = campanhaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Campanha> insert(@RequestBody CampanhaRequest obj){
        verificaDataCampanhasExistentes(obj.transformaCampanhaRequestParaCampanha());
        Campanha campanha = campanhaService.insert(obj.transformaCampanhaRequestParaCampanha());
        return new ResponseEntity(CampanhaResponse.transformaCampanhaEmCampanhaResponse(campanha), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        campanhaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Campanha> update(@PathVariable Long id, @RequestBody CampanhaRequest obj){
        Campanha campanha = campanhaService.update(id, obj.transformaCampanhaRequestParaCampanha());
        return new ResponseEntity(CampanhaResponse.transformaCampanhaEmCampanhaResponse(campanha), HttpStatus.OK);
    }

    private List<Campanha> verificaCampanhasVigentes(List<Campanha> list){
        List<Campanha> listAux = new ArrayList<>();
        for(Campanha campanha : list){
            if (campanha.getDataFimVigencia().isAfter(LocalDate.now()) ||
                campanha.getDataFimVigencia().isEqual(LocalDate.now())){
                listAux.add(campanha);
            }
        }
        return listAux;
    }

    private void verificaDataCampanhasExistentes(Campanha obj) {
        List<Campanha> listAux = campanhaService.findAll();
        for(Campanha campanha : listAux){
            if (campanha.getDataFimVigencia().isEqual(obj.getDataFimVigencia())) {
                LocalDate dataAuxiliar = campanha.getDataFimVigencia();
                campanha.setDataFimVigencia(dataAuxiliar.plusDays(1));
                campanhaService.update(campanha.getId(), campanha);
            }
        }
    }
}
