package com.example.democampanha.resources;

import com.example.democampanha.models.Campanha;
import com.example.democampanha.models.Cliente;
import com.example.democampanha.services.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Campanha> insert(@RequestBody Campanha obj){
        verificaDataCampanhasExistentes(obj);
        obj = campanhaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        campanhaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Campanha> update(@PathVariable Long id, @RequestBody Campanha obj){
        obj = campanhaService.update(id, obj);
        return ResponseEntity.ok().body(obj);
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
