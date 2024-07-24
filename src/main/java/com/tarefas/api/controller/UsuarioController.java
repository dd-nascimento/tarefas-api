package com.tarefas.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.model.Usuario;
import com.tarefas.api.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
 
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity <Usuario> cadastrarUsuarios (@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
    }
    
    @GetMapping
    public ResponseEntity <List <Usuario>> listarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity <Usuario> consultarUsuario(@PathVariable("id") Long id){
        
        Usuario usuario = usuarioService.buscarUsuarioPeloId(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deletarUsuario (@PathVariable("id") Long id){
        
        Usuario usuario = usuarioService.buscarUsuarioPeloId(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        usuarioService.deletarUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario (@PathVariable("id") Long id, @RequestBody Usuario dadosUsuario){
        return usuarioService.atualizarUsuario(id, dadosUsuario);
        
    }
}
