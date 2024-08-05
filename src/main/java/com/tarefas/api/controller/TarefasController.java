package com.tarefas.api.controller;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.dto.TarefaDTO;
import com.tarefas.api.model.Tarefas;
import com.tarefas.api.service.TarefaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity <Tarefas> cadastrarNovaTarefa(@Valid @RequestBody Tarefas tarefas){
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.cadastrarNovaTarefa(tarefas));
    }

    @GetMapping
    public ResponseEntity <Page <TarefaDTO>> listarTarefas(Pageable paginacao){
        return ResponseEntity.status(HttpStatus.OK).body( tarefaService.listarTarefas(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity <TarefaDTO> consultarTarefaPorId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.buscarTarefaPeloId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deletarTarefa (@PathVariable("id") Long id){
        
        TarefaDTO tarefas = tarefaService.buscarTarefaPeloId(id);

        if (Objects.isNull(tarefas)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        tarefaService.deletarTarefa(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    
}
