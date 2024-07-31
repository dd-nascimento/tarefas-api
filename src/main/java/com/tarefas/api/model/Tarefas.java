package com.tarefas.api.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tarefas.api.constants.Prioridade;
import com.tarefas.api.constants.StatusTarefa;
import com.tarefas.api.dto.TarefaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_tarefas")
public class Tarefas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TAREFA")
    private long id;

    @Column(name = "NOME_TAREFA", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO_TAREFA")
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATA_CRIACAO_TAREFA", nullable = false)
    private LocalDate dataDeCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATA_CONCLUSAO_TAREFA")
    private LocalDate dataDeConclusao;

    @Column(name = "PRIORIDADE_TAREFA", nullable = false)
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(name = "STATUS_TAREFA", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    public TarefaDTO toDTO(){

        TarefaDTO dto = new TarefaDTO();

        dto.setId(id);
        dto.setNome(nome);
        dto.setDescricao(descricao);
        dto.setDataCriacao(dataDeCriacao);

        Period periodo = null;

        if (Objects.isNull(dataDeConclusao)) {

            periodo = Period.between(dataDeCriacao, LocalDate.now());
            
        } else {
            periodo = Period.between(dataDeCriacao, dataDeConclusao);
        }

        dto.setDiasTrabalhados(periodo.getDays()/365);
        dto.setPrioridade(prioridade);
        dto.setStatus(status);

        return dto;
    }
}
