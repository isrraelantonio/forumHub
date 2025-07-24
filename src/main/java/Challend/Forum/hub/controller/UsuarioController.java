package Challend.Forum.hub.controller;


import Challend.Forum.hub.domain.curso.Curso;
import Challend.Forum.hub.domain.curso.DadosCurso;
import Challend.Forum.hub.domain.usuario.DadosUsuario;
import Challend.Forum.hub.domain.usuario.Usuario;
import Challend.Forum.hub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public ResponseEntity CriacaoDoUsuario(@RequestBody @Valid DadosUsuario dados){
        var dadosDoUsuario = new DadosUsuario(dados.nome(), dados.email(), dados.senha());
        var usuarioEmSi = new Usuario(dadosDoUsuario);
        usuarioRepository.save(usuarioEmSi);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(usuarioEmSi.getId())
                .toUri();

        return ResponseEntity.created(location).body(dadosDoUsuario);

    }


}
