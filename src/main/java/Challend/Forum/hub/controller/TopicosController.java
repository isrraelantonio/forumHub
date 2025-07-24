package Challend.Forum.hub.controller;


import Challend.Forum.hub.ValidacaoException;
import Challend.Forum.hub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/topico")
public class TopicosController {

    @Autowired
    private  CriarTopicos criarTopicos;

    @Autowired
    private TopicoRepository topicoRepository;


    @Transactional
    @PostMapping
    public ResponseEntity CriarTopicos(@RequestBody @Valid DadosTopico dados){
        var topicoEmSi = criarTopicos.criandoTopico(dados);
        var detalhesTopico = new DetalhamentoTopico(topicoEmSi);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(topicoEmSi.getId())
                .toUri();

        return ResponseEntity.created(location).body(detalhesTopico);
    }

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity atualizarTopico(@RequestBody @Valid DadosTopico dados, @PathVariable Long id){
        var topicoEmSi = criarTopicos.atualizandoTopico(dados, id);
        return ResponseEntity.ok(topicoEmSi);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity DeletarTopico(@PathVariable Long id){
        Optional<Topico> topicoEmSi = Optional.ofNullable(topicoRepository.topicoPeloId(id));
        if(topicoEmSi.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }



// Esse é apenas um exemplo em caso que se quisesse apenas exibir uma lista de tópicos que não fosse uma paginação.
//   @GetMapping
//   public List<DetalhamentoTopico> listagemDeTopicos(){
//        var detalhesTopico = topicoRepository.findByEstadoDoTopicoTrue().stream()
//                 .map(DetalhamentoTopico::new).toList();
//        return detalhesTopico;
//
//   }

// Esa é a forma de retornar uma lista do tipo  page no formato JSON
    @GetMapping
    public ResponseEntity< Page<ListagemTopicos>> listagemDeTopicos(@PageableDefault(sort = "dataCriacao") Pageable paginacao){
        var paginacaoListagemtopicos = topicoRepository.findByEstadoDoTopicoTrue(paginacao)
                .map(ListagemTopicos::new);

        return ResponseEntity.ok(paginacaoListagemtopicos);

    }


    @GetMapping("/{curso}")
    public ResponseEntity< Page<ListagemTopicos>> listagemDeTopicosCurso(@PageableDefault(sort = "dataCriacao") Pageable paginacao,@PathVariable String curso){
        var paginacaoListagemtopicos = topicoRepository.topicoPeloCurso(paginacao, curso)
                .map(ListagemTopicos::new);

        return ResponseEntity.ok(paginacaoListagemtopicos);

    }

    @GetMapping("/{curso}/{ano}")
    public ResponseEntity< Page<ListagemTopicos>> topicoPeloano(@PageableDefault(sort = "data_criacao") Pageable paginacao,@PathVariable String ano, @PathVariable String curso){
        var paginacaoListagemtopicos = topicoRepository.topicoPeloano(paginacao, curso, ano)
                .map(ListagemTopicos::new);

        return ResponseEntity.ok(paginacaoListagemtopicos);

    }

    @GetMapping("id/{id}")
    public ResponseEntity topicoPeloId( @PathVariable Long id){
        var topicoPeloId = topicoRepository.topicoPeloId(id);
        var topicoPeloId2 = new DetalhamentoTopico(topicoPeloId);
        return ResponseEntity.ok(topicoPeloId2);

    }


}
