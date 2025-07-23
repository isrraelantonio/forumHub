package Challend.Forum.hub.domain.topico;


import Challend.Forum.hub.ValidacaoException;
import Challend.Forum.hub.domain.curso.CursoRepository;
import Challend.Forum.hub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarTopicos {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;




    public DetalhamentoTopico criandoTopico(DadosTopico dados){

        if(!usuarioRepository.existsById(dados.autor())){
            throw  new ValidacaoException("o autor informado não existe!");

        }
        if(dados.curso() != null && !cursoRepository.existsById(dados.curso())){
            throw  new ValidacaoException("O curso informado não existe!");
        }


//        validadores.forEach(v-> v.validar(dados));
        var usuario = usuarioRepository.getReferenceById(dados.autor());
        var curso = cursoRepository.getReferenceById(dados.curso());
        var topico = new Topico(dados.titulo(), dados.mensagem(), usuario, curso);
        topicoRepository.save(topico);




        return new DetalhamentoTopico(topico);



    }


    public DetalhamentoTopico atualizandoTopico(@Valid DadosTopico dados, Long id) {

        if(!topicoRepository.existsById(id)){
            throw  new ValidacaoException("O topico não existe ou está fora do período de interação.");
        }
        if(!usuarioRepository.existsById(dados.autor())){
            throw  new ValidacaoException("o autor informado não existe!");

        }
        if(dados.curso() != null && !cursoRepository.existsById(dados.curso())){
            throw  new ValidacaoException("O curso informado não existe!");
        }

        var usuario = usuarioRepository.getReferenceById(dados.autor());
        var curso = cursoRepository.getReferenceById(dados.curso());

        var topicoEmsi = topicoRepository.topicoPeloId(id);

        topicoEmsi.atualizarDadosTopicos(dados, curso, usuario);
        return new DetalhamentoTopico(topicoEmsi);




    }
}
