package com.tarefas.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.model.Tarefas;
import com.tarefas.api.repository.TarefaRepository;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {
    
    @Autowired
    private TarefaRepository tarefaRepository;

    @PostMapping
    public Tarefas cadastrarTarefas(@RequestBody Tarefas tarefas){
        return tarefaRepository.save(tarefas);
    }

    @GetMapping
    public List <Tarefas> listarTarefas(){
        return tarefaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional <Tarefas> consultarTarefaPorId(@PathVariable("id") Long id){
        return tarefaRepository.findById(id);
    }

    @GetMapping("/{prioridade}")
    public Optional<Tarefas> listarPorPrioridade(@PathVariable("prioridade") String prioridade){
        return tarefaRepository.findById(prioridade);
    }
    
    @PostMapping("/remover/{id}")
    public Optional <Tarefas> removerTarefa(@PathVariable("id") Long id){
        return tarefaRepository.delete(id);
    }
}
