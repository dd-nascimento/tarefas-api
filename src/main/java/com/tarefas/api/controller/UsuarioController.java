package com.tarefas.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.dto.UsuarioDTO;
import com.tarefas.api.model.Usuario;
import com.tarefas.api.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuarios(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> listarUsuarios
        (@PageableDefault(size = 20, page = 1, sort = "nome", direction = Direction.DESC) Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> consultarUsuario(@PathVariable("id") Long id) {

        UsuarioDTO usuario = usuarioService.buscarUsuarioPeloId(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioDTO> consultarUsuarioPorCpf(@PathVariable("cpf") String cpf) {

        UsuarioDTO usuario = usuarioService.buscarUsuarioPeloCpf(cpf);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @GetMapping("/busca")
    public ResponseEntity <List <UsuarioDTO>> filtrarUsuarioPeloNome (@RequestParam("nome") String nome){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.filtrarUsuarioPeloNome(nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Long id) {

        UsuarioDTO usuario = usuarioService.buscarUsuarioPeloId(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        usuarioService.deletarUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@Valid @PathVariable("id") Long id, @RequestBody Usuario dadosUsuario) {

        UsuarioDTO usuario = usuarioService.buscarUsuarioPeloId(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizarUsuario(id, dadosUsuario));

    }
}
