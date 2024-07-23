package com.tarefas.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.model.Usuario;
import com.tarefas.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
 
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public Usuario cadastrarUsuarios(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    @GetMapping
    public List <Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional <Usuario> consultarUsuario(@PathVariable("id") Long id){
        return usuarioRepository.findById(id);
    }
}
