package com.example.democampanha.controller;

import com.example.democampanha.dto.TorcedorRequest;
import com.example.democampanha.dto.TorcedorResponse;
import com.example.democampanha.services.TorcedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/torcedores")
@RequiredArgsConstructor
public class TorcedorController {

    private final TorcedorService torcedorService;

    @GetMapping
    public ResponseEntity<List<TorcedorResponse>> buscarTodos(){
        List<TorcedorResponse> listarTorcedores = torcedorService.buscarTodos();
        return ResponseEntity.ok(listarTorcedores);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TorcedorResponse> buscarPorId(@PathVariable Long id){
        TorcedorResponse TorcedorResponse = torcedorService.buscarPorId(id);
        return ResponseEntity.ok().body(TorcedorResponse);
    }

    @PostMapping
    public ResponseEntity<TorcedorResponse> salvar(@RequestBody @Valid TorcedorRequest torcedorRequest){
        TorcedorResponse torcedorResponse = torcedorService.salvar(torcedorRequest);
        return ResponseEntity.ok(torcedorResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id){
        torcedorService.apagar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TorcedorResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid TorcedorRequest TorcedorRequest){
        TorcedorResponse TorcedorResponse = torcedorService.atualizar(id, TorcedorRequest);
        return ResponseEntity.ok(TorcedorResponse);
    }

}
