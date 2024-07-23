package com.tarefas.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.model.Tarefas;
import com.tarefas.api.repository.TerafasRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    private TerafasRepository tarefasRepository;

    @PostMapping
    public Tarefas cadastrarNovaTarefa(@RequestBody Tarefas tarefas){
        return tarefasRepository.save(tarefas);
    }

    @GetMapping
    public List <Tarefas> listarTarefas(){
        return tarefasRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional <Tarefas> consultarTarefaPorId(@PathVariable("id") Long id){
        return tarefasRepository.findById(id);
    }
    
}
