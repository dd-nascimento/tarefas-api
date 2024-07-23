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

import com.tarefas.api.model.Projetos;
import com.tarefas.api.repository.ProjetoRepository;

@RestController
@RequestMapping("/projetos")
public class ProjetosController {
    
    @Autowired
    private ProjetoRepository projetoRepository;

    @PostMapping
    public Projetos cadastrarProjetos(@RequestBody Projetos projetos){
        return projetoRepository.save(projetos);
    }

    @GetMapping
    public List <Projetos> listarProjetos(){
        return projetoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional <Projetos> consultarProjeto(@PathVariable("id") Long id){
        return projetoRepository.findById(id);
    }

}
