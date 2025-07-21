package Challend.Forum.hub.controller;


import Challend.Forum.hub.domain.curso.Curso;
import Challend.Forum.hub.domain.curso.DadosCurso;
import Challend.Forum.hub.domain.usuario.DadosUsuario;
import Challend.Forum.hub.domain.usuario.Usuario;
import Challend.Forum.hub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public Usuario CriacaoDoUsuario(@RequestBody @Valid DadosUsuario dados){
        var dadosDoUsuario = new DadosUsuario(dados.nome(), dados.email(), dados.senha());
        var usuarioEmSi = new Usuario(dadosDoUsuario);
        usuarioRepository.save(usuarioEmSi);

        return usuarioEmSi;

    }


}
