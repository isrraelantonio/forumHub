package Challend.Forum.hub.controller;


import Challend.Forum.hub.domain.curso.Curso;
import Challend.Forum.hub.domain.curso.CursoRepository;
import Challend.Forum.hub.domain.curso.DadosCurso;
import Challend.Forum.hub.domain.topico.DadosTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    @PostMapping
    public Curso criacaodocurso(@RequestBody @Valid DadosCurso dados){
        var dadosDoCurso = new DadosCurso(dados.nome(), dados.categoria());
        var cursoEmSi = new Curso(dadosDoCurso);
        cursoRepository.save(cursoEmSi);

        return cursoEmSi;

    }

}
