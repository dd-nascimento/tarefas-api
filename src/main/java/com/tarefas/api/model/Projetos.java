package com.tarefas.api.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.tarefas.api.constants.StatusTarefa;
import com.tarefas.api.dto.ProjetoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_projetos")
public class Projetos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROJETO")
    private long id;

    @Column(name = "NOME_PROJETO", nullable = false)
    @NotNull
    private String nome;

    @Column(name = "DESCRICAO_PROJETO")
    private String descricao;

    @OneToMany
    @JoinTable(
        name = "tb_projetos_tarefas",
        joinColumns = @JoinColumn(name = "ID_PROJETO"),
        inverseJoinColumns = @JoinColumn (name = "ID_TAREFA")
    )
    private List<Tarefas> tarefas;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_RESP")
    private Usuario responsavel;

    public ProjetoDTO toDTO(){

        ProjetoDTO dto = new ProjetoDTO();

        dto.setId(id);
        dto.setNome(nome);
        dto.setDescricao(descricao);

        if (Objects.nonNull(responsavel)) {
            
            dto.setNomeResponsavel(responsavel.getNome());
        }

        List<Tarefas> pendentes = tarefas.stream()
                                .filter(tarefa -> StatusTarefa.PENDENTE.equals(tarefa.getStatus()))
                                .collect(Collectors.toList());
        
        List<Tarefas> emAndamento = tarefas.stream()
                                .filter(tarefa -> StatusTarefa.FAZENDO.equals(tarefa.getStatus()))
                                .collect(Collectors.toList());   
        
        List<Tarefas> finalizadas = tarefas.stream()
                                .filter(tarefa -> StatusTarefa.FINALIZADA.equals(tarefa.getStatus()))
                                .collect(Collectors.toList());

        dto.setQtdeTarefasFazendo(pendentes.size());
        dto.setQtdeTarefasPendentes(emAndamento.size());
        dto.setQtdeTarefasConcluidas(finalizadas.size());

        return dto;
    }
    
}
