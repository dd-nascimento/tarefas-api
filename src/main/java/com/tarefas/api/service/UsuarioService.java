package com.tarefas.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefas.api.dto.UsuarioDTO;
import com.tarefas.api.model.Usuario;
import com.tarefas.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioDTO> listarUsuarios() {

        List<Usuario> usuarios = usuarioRepository.findAll();


        /* Função Lambda em JAVA */
        return usuarios.stream()
                .map(usuario -> usuario.toDTO())
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarUsuarioPeloId(Long id) {

        Optional <Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get().toDTO();
        }

        return null;
    }

    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizarUsuario(Long id, Usuario dadosUsuario){

        Optional <Usuario> usuarioOpt = usuarioRepository.findById(id);
        
        if (usuarioOpt.isPresent()){
            Usuario usuario = usuarioOpt.get();

            usuario.setNome(dadosUsuario.getNome());
            usuario.setCpf(dadosUsuario.getCpf());
            usuario.setDataNascimento(dadosUsuario.getDataNascimento());
            usuario.setEmail(dadosUsuario.getEmail());
            usuario.setSenha(dadosUsuario.getSenha());
            usuario.setStatus(dadosUsuario.getStatus());

            usuarioRepository.save(usuario);
        }

        return null;
        
    }
}
