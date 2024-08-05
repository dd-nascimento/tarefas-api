package com.tarefas.api.model;

import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tarefas.api.constants.StatusUsuario;
import com.tarefas.api.dto.UsuarioDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Campo nome é Obrigatório!") /* A aplicação não irá receber o objeto JSON sem o nome */
    @Column(name = "NOME_USUARIO", nullable = false)
    private String nome;

    @NotNull(message = "Obrigatório informar o CPF!")
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

    @Column(name = "STATUS_USUARIO", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusUsuario status;

    public UsuarioDTO toDTO(){

        UsuarioDTO dto =  new UsuarioDTO();

        dto.setId(id);
        dto.setNome(nome);
        dto.setEmail(email);
        dto.setCpf(cpf);

        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dataAtual);

        dto.setIdade(periodo.getYears());
        dto.setStatus(status);

        return dto;
    }

    
}
