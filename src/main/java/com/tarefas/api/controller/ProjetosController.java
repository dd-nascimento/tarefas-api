package com.tarefas.api.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.model.Projetos;
import com.tarefas.api.service.ProjetoService;

@RestController
@RequestMapping("/projetos")
public class ProjetosController {
    
    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity <Projetos> cadastrarProjetos(@RequestBody Projetos projetos){
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoService.cadastrarProjetos(projetos));
    }

    @GetMapping
    public ResponseEntity <List <Projetos>> listarProjetos(){
        return ResponseEntity.status(HttpStatus.OK).body(projetoService.listarProjetos());
    }

    @GetMapping("/{id}")
    public ResponseEntity <Projetos> consultarProjeto(@PathVariable("id") Long id){

        Projetos projeto = projetoService.consultaProjetoPorId(id);

        if (Objects.isNull(projeto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(projeto);

    }

    @DeleteMapping
    public ResponseEntity <Void> deletarProjeto (@PathVariable ("id") Long id){

        Projetos projeto = projetoService.consultaProjetoPorId(id);

        if (Objects.isNull(projeto)) {
            
            return ResponseEntity.notFound().build();
        }

        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity <Projetos> atualizarProjetos (@PathVariable("id") Long id, @RequestBody Projetos dadosProjeto){

        Projetos projeto = projetoService.consultaProjetoPorId(id);

        if(Objects.isNull(projeto)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(projetoService.atualizarProjeto(id, dadosProjeto));
    }

}
