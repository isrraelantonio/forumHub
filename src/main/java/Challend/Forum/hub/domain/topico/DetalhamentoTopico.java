package Challend.Forum.hub.domain.topico;

import Challend.Forum.hub.domain.curso.Curso;
import Challend.Forum.hub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetalhamentoTopico(

        Long id,

        String titulo,

        String mensagem,

        Long Idautor,

        Long Idcurso
                                 ) {
    public DetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor().getId(), topico.getCurso().getId());

    }
}
