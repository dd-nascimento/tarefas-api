package com.tarefas.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tarefas.api.dto.ProjetoDTO;
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

    public Page <ProjetoDTO> listarProjetos(Pageable paginacao){
        return projetoRepository.findAll(paginacao).map(projetos -> projetos.toDTO());
    }

    public ProjetoDTO consultaProjetoPorId(Long id){
        
        Optional <Projetos> projetoOpt = projetoRepository.findById(id);

        /* isPresent verifica se o objeto está present ou não */
        if (projetoOpt.isPresent()) {
            return projetoOpt.get().toDTO();
        }

        return null;
    }

    public List <ProjetoDTO> consultaProjetoPeloResponsavelId(Long id){
        
        List <Projetos> projetos = projetoRepository.findByResponsavel_id(id);


        return projetos.stream().map(Projetos::toDTO).toList();
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
