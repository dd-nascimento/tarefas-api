package com.tarefas.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefas.api.model.Projetos;
import com.tarefas.api.repository.ProjetoRepository;

@Service
public class ProjetoService {
    
    @Autowired
    private ProjetoRepository projetoRepository;

    /* Construir o SERVICE */
    public Projetos cadastrarProjetos (Projetos projetos){
        return projetoRepository.save(projetos);
    }

    public List <Projetos> listarProjetos(){
        return projetoRepository.findAll();
    }

    public Projetos consultaProjetoPorId(Long id){
        
        Optional <Projetos> projetoOpt = projetoRepository.findById(id);

        /* isPresent verifica se o objeto está present ou não */
        if (projetoOpt.isPresent()) {
            return projetoOpt.get();
        }

        return null;
    }

    public void deletarProjeto (Long id){
        projetoRepository.deleteById(id);
    }

    public Projetos atualizarProjeto (Long id, Projetos dadProjetos){

        Optional <Projetos> projetoOpt = projetoRepository.findById(id);

        if (projetoOpt.isPresent()) {
            Projetos projetos = projetoOpt.get();

            projetos.setId(id);
            projetos.setNome(dadProjetos.getNome());
            projetos.setDescricao(dadProjetos.getDescricao());
            projetos.setResponsavel(dadProjetos.getResponsavel());
            projetos.setTarefas(dadProjetos.getTarefas());

            return projetoRepository.save(projetos);
        }

        return null;
    }


}
