package com.tarefas.api.model;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    
}
