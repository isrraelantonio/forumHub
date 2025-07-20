package Challend.Forum.hub.controller;


import Challend.Forum.hub.domain.topico.DadosTopico;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topico")
public class TopicosController {

    @PostMapping
    public DadosTopico primeiraRequest(@RequestBody @Valid DadosTopico dados){
        var topico = new DadosTopico(dados.titulo(), dados.mensagem(),dados.data(),dados.autor(), dados.curso());
        return topico;
    }

}
