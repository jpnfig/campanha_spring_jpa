package com.example.democampanha.controller;

import com.example.democampanha.dto.CampanhaRequest;
import com.example.democampanha.dto.CampanhaResponse;
import com.example.democampanha.models.enums.TimeCoracao;
import com.example.democampanha.services.CampanhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/campanhas")
@RequiredArgsConstructor
public class CampanhaController {

    private final CampanhaService campanhaService;

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

//  /campanhas/time/SAO_PAULO
    @GetMapping(value = "/time/{timeCoracao}")
    public ResponseEntity<List<CampanhaResponse>> buscarPorTimeCoracao(@PathVariable TimeCoracao timeCoracao){
        List<CampanhaResponse> list = campanhaService.buscarPorTimeCoracao(timeCoracao);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<CampanhaResponse> salvar(@RequestBody @Valid CampanhaRequest campanhaRequest){
        CampanhaResponse campanhaResponse = campanhaService.salvar(campanhaRequest);
        return ResponseEntity.ok(campanhaResponse);
    }

//  /campanhas/1/adicionartorcedor/1
    @PatchMapping("/{idCampanha}/adicionartorcedor/{idTorcedor}")
    public ResponseEntity<CampanhaResponse> salvarCampanhaTorcedor(
            @PathVariable Long idCampanha,
            @PathVariable Long idTorcedor){
        CampanhaResponse campanhaResponse = campanhaService.adicionarTorcedorACampanha(idCampanha, idTorcedor);
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
            @RequestBody @Valid CampanhaRequest campanhaRequest){
        CampanhaResponse campanhaResponse = campanhaService.atualizar(id, campanhaRequest);
        return ResponseEntity.ok(campanhaResponse);
    }

}
