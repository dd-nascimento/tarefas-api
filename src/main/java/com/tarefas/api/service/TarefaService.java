package com.tarefas.api.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tarefas.api.dto.TarefaDTO;
import com.tarefas.api.model.Tarefas;
import com.tarefas.api.repository.TarefaRepository;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefas cadastrarNovaTarefa (Tarefas tarefas){
        return tarefaRepository.save(tarefas);
    }

    public Page <TarefaDTO> listarTarefas (Pageable paginacao){
        return tarefaRepository.findAll(paginacao).map(tarefas -> tarefas.toDTO());
    }

    public TarefaDTO buscarTarefaPeloId (Long id){

        Optional <Tarefas> tarefasOpt = tarefaRepository.findById(id);
        if (tarefasOpt.isPresent()) {
            return tarefasOpt.get().toDTO();
        }

        return null;
    }

    public void deletarTarefa(Long id){
        tarefaRepository.deleteById(id);
    }
}
