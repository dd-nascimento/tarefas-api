package com.tarefas.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List <TarefaDTO> listarTarefas (){
        return tarefaRepository.findAll()
            .stream().map(Tarefas::toDTO)
            .collect(Collectors.toList());
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
