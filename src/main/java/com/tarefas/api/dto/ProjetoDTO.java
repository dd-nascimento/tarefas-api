package com.tarefas.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoDTO {
    
    private Long id;
    private String nome;
    private String descricao;
    private String nomeResponsavel;
    private int qtdeTarefasPendentes;
    private int qtdeTarefasFazendo;
    private int qtdeTarefasConcluidas;

}
