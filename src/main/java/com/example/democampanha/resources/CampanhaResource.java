package com.example.democampanha.resources;

import com.example.democampanha.models.Campanha;
import com.example.democampanha.services.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/campanhas")
public class CampanhaResource {

    @Autowired
    private CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<Campanha>> findAll(){
        List<Campanha> list = campanhaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Campanha> findById(@PathVariable Long id){
        Campanha obj = campanhaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Campanha> insert(@RequestBody Campanha obj){
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
}
