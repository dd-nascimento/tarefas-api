package com.tarefas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarefas.api.model.Tarefas;

@Repository
public interface TerafasRepository extends JpaRepository<Tarefas, Long>{

}
