package Challend.Forum.hub.domain.curso;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Curso")
@Table (name = "cursos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")

public class Curso {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(DadosCurso dados) {
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }
}
