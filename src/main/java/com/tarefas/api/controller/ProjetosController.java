package com.tarefas.api.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.dto.ProjetoDTO;
import com.tarefas.api.model.Projetos;
import com.tarefas.api.service.ProjetoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projetos")
public class ProjetosController {
    
    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity <Projetos> cadastrarProjetos(@Valid @RequestBody Projetos projetos){
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoService.cadastrarProjetos(projetos));
    }

    @GetMapping
    public ResponseEntity<Page<ProjetoDTO>> listarProjetos(Pageable paginacao){
        return ResponseEntity.status(HttpStatus.OK).body(projetoService.listarProjetos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity <ProjetoDTO> consultarProjeto(@PathVariable("id") Long id){

        ProjetoDTO projeto = projetoService.consultaProjetoPorId(id);

        if (Objects.isNull(projeto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(projeto);

    }

    @GetMapping("/responsavel/{id}")
    public ResponseEntity <List <ProjetoDTO>> consultarProjetoPeloREsponsavel(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(projetoService.consultaProjetoPeloResponsavelId(id));
    }

    @DeleteMapping
    public ResponseEntity <Void> deletarProjeto (@PathVariable ("id") Long id){

        ProjetoDTO projeto = projetoService.consultaProjetoPorId(id);

        if (Objects.isNull(projeto)) {
            
            return ResponseEntity.notFound().build();
        }

        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity <Projetos> atualizarProjetos (@PathVariable("id") Long id, @RequestBody Projetos dadosProjeto){

        ProjetoDTO projeto = projetoService.consultaProjetoPorId(id);

        if(Objects.isNull(projeto)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(projetoService.atualizarProjeto(id, dadosProjeto));
    }

}
