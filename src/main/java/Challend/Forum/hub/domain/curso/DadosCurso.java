package Challend.Forum.hub.domain.curso;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCurso (
        @NotBlank
        String nome,
        @NotNull @Valid
        Categoria categoria
){
}
