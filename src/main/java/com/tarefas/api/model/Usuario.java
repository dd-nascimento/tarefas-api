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
@Entity(name = "tb_usuarios")
public class Usuario {
    
    /* Anotação para identificar a chave primaria da Tabela */
    @Id
    /* Identificando que é um campo auto increment */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* Customização do nome do atributo no banco */
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOME_USUARIO", nullable = false)
    private String nome;

    @Column(name = "CPF_USUARIO", nullable = false, unique = true)
    private String cpf;

    /* unique = true vai difinir que o atributo será único na tabela. */
    @Column(name = "EMAIL_USUARIO", nullable = false, unique = true)
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DTA_NASC_USUARIO", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "SENHA_USUARIO", nullable = false)
    private String senha;

    @Column(name = "USUARIO_ATIVO", nullable = false)
    private boolean ativo;

    
}
