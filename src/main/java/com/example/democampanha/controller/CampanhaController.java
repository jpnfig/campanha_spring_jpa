package com.example.democampanha.controller;

import com.example.democampanha.dto.CampanhaRequest;
import com.example.democampanha.dto.CampanhaResponse;
import com.example.democampanha.services.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/campanhas")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<CampanhaResponse>> buscarTodos(){
        List<CampanhaResponse> list = campanhaService.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CampanhaResponse> buscarPorId(@PathVariable Long id){
        CampanhaResponse campanhaResponse = campanhaService.buscarPorId(id);
        return ResponseEntity.ok().body(campanhaResponse);
    }

    @PostMapping
    public ResponseEntity<CampanhaResponse> salvar(@RequestBody CampanhaRequest campanhaRequest){
        CampanhaResponse campanhaResponse = campanhaService.salvar(campanhaRequest);
        return ResponseEntity.ok(campanhaResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id){
        campanhaService.apagar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CampanhaResponse> atualizar(
            @PathVariable Long id,
            @RequestBody CampanhaRequest campanhaRequest){
        CampanhaResponse campanhaResponse = campanhaService.atualizar(id, campanhaRequest);
        return ResponseEntity.ok(campanhaResponse);
    }

}
