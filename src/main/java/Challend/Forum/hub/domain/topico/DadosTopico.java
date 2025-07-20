package Challend.Forum.hub.domain.topico;

import Challend.Forum.hub.domain.curso.Curso;
import Challend.Forum.hub.domain.curso.DadosCurso;
import Challend.Forum.hub.domain.usuario.DadosUsuario;
import Challend.Forum.hub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        LocalDateTime data,
        @NotNull @Valid
        DadosUsuario autor,
        @NotNull
        DadosCurso curso
) {
}
