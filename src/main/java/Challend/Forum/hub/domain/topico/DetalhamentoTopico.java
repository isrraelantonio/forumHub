package Challend.Forum.hub.domain.topico;

import Challend.Forum.hub.domain.curso.Curso;
import Challend.Forum.hub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DetalhamentoTopico(

        Long id,

        String titulo,

        String mensagem,

        Long Idautor,

        Long Idcurso,

        LocalDateTime dataCriacao
                                 ) {
    public DetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor().getId(), topico.getCurso().getId(), topico.getDataCriacao());

    }

}
