package Challend.Forum.hub.domain.topico;

import java.time.LocalDateTime;

public record ListagemTopicos (
        String titulo,

        String mensagem,

        String nomeAutor,

        String nomeCurso,

        LocalDateTime dataCriacao
) {
    public ListagemTopicos(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getAutor().getNome(), topico.getCurso().getNome(), topico.getDataCriacao());

    }

}
