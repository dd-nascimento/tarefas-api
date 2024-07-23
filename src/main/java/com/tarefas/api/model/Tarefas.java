package com.tarefas.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private String prioridade;

    @Column(name = "STATUS_TAREFA", nullable = false)
    private String status;
}
