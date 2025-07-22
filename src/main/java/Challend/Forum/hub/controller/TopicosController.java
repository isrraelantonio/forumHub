package Challend.Forum.hub.controller;


import Challend.Forum.hub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topico")
public class TopicosController {

    @Autowired
    private  CriarTopicos criarTopicos;

    @Autowired
    private TopicoRepository topicoRepository;


    @Transactional
    @PostMapping
    public DetalhamentoTopico primeiraRequest(@RequestBody @Valid DadosTopico dados){
        var topicoEmSi = criarTopicos.criandoTopico(dados);
        return topicoEmSi;
    }


// Esse é apenas um exemplo em caso que se quisesse apenas exibir uma lista de tópicos.
//   @GetMapping
//   public List<DetalhamentoTopico> listagemDeTopicos(){
//        var detalhesTopico = topicoRepository.findByEstadoDoTopicoTrue().stream()
//                 .map(DetalhamentoTopico::new).toList();
//        return detalhesTopico;
//
//   }

// Esa é a forma de retornar uma lista do tipo  page no formato JSON
    @GetMapping
    public Page<DetalhamentoTopico> listagemDeTopicos(@PageableDefault(sort = "dataCriacao") Pageable paginacao){
        var paginacaodetalhesTopico = topicoRepository.findByEstadoDoTopicoTrue(paginacao)
                .map(DetalhamentoTopico::new);

        return paginacaodetalhesTopico;

    }


    @GetMapping("/{curso}")
    public Page<DetalhamentoTopico> listagemDeTopicosCurso(@PageableDefault(sort = "dataCriacao") Pageable paginacao,@PathVariable String curso){
        var paginacaodetalhesTopico = topicoRepository.topicoPeloCurso(paginacao, curso)
                .map(DetalhamentoTopico::new);

        return paginacaodetalhesTopico;

    }

    @GetMapping("/{curso}/{ano}")
    public Page<DetalhamentoTopico> topicoPeloano(@PageableDefault(sort = "data_criacao") Pageable paginacao,@PathVariable String ano, @PathVariable String curso){
        var paginacaodetalhesTopico = topicoRepository.topicoPeloano(paginacao, curso, ano)
                .map(DetalhamentoTopico::new);

        return paginacaodetalhesTopico;

    }


}
