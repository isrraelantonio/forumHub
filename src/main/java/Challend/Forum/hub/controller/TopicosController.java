package Challend.Forum.hub.controller;


import Challend.Forum.hub.ValidacaoException;
import Challend.Forum.hub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public DetalhamentoTopico primeiraRequest(@RequestBody @Valid DadosTopico dados){
        var topicoEmSi = criarTopicos.criandoTopico(dados);
        return topicoEmSi;
    }

    @Transactional
    @PostMapping("/{id}")
    public DetalhamentoTopico atualizarTopico(@RequestBody @Valid DadosTopico dados, @PathVariable Long id){
        var topicoEmSi = criarTopicos.atualizandoTopico(dados, id);
        return topicoEmSi;
    }

    @Transactional
    @DeleteMapping("/{id}")
    public String DeletarTopico(@PathVariable Long id){
        Optional<Topico> topicoEmSi = Optional.ofNullable(topicoRepository.topicoPeloId(id));
        if(topicoEmSi.isPresent()){
            topicoRepository.deleteById(id);
             String resposta = "Tópico excluído";
            return resposta;
        }else {
             throw new ValidacaoException("O tópico informado já não existe em nosso banco de dados.");
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
    public Page<ListagemTopicos> listagemDeTopicos(@PageableDefault(sort = "dataCriacao") Pageable paginacao){
        var paginacaoListagemtopicos = topicoRepository.findByEstadoDoTopicoTrue(paginacao)
                .map(ListagemTopicos::new);

        return paginacaoListagemtopicos;

    }


    @GetMapping("/{curso}")
    public Page<ListagemTopicos> listagemDeTopicosCurso(@PageableDefault(sort = "dataCriacao") Pageable paginacao,@PathVariable String curso){
        var paginacaoListagemtopicos = topicoRepository.topicoPeloCurso(paginacao, curso)
                .map(ListagemTopicos::new);

        return paginacaoListagemtopicos;

    }

    @GetMapping("/{curso}/{ano}")
    public Page<ListagemTopicos> topicoPeloano(@PageableDefault(sort = "data_criacao") Pageable paginacao,@PathVariable String ano, @PathVariable String curso){
        var paginacaoListagemtopicos = topicoRepository.topicoPeloano(paginacao, curso, ano)
                .map(ListagemTopicos::new);

        return paginacaoListagemtopicos;

    }

    @GetMapping("id/{id}")
    public DetalhamentoTopico topicoPeloId( @PathVariable Long id){
        var topicoPeloId = topicoRepository.topicoPeloId(id);
        var topicoPeloId2 = new DetalhamentoTopico(topicoPeloId);
        return topicoPeloId2;

    }


}
