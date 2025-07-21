package Challend.Forum.hub.controller;


import Challend.Forum.hub.domain.curso.CursoRepository;
import Challend.Forum.hub.domain.topico.*;
import Challend.Forum.hub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topico")
public class TopicosController {

    @Autowired
    private  CriarTopicos criarTopicos;


    @Transactional
    @PostMapping
    public DetalhamentoTopico primeiraRequest(@RequestBody @Valid DadosTopico dados){
        var topicoEmSi = criarTopicos.criandoTopico(dados);
        return topicoEmSi;
    }

}
