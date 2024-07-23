package com.tarefas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarefas.api.model.Projetos;

@Repository
public interface ProjetoRepository extends JpaRepository <Projetos, Long> {
    
}
