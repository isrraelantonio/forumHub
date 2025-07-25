package Challend.Forum.hub.domain.topico;

import Challend.Forum.hub.domain.curso.Curso;
import Challend.Forum.hub.domain.resposta.Resposta;
import Challend.Forum.hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String  mensagem;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private boolean estadoDoTopico = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany (mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resposta> resposta;

    public Topico(String titulo, String mensagem , Usuario usuario, Curso curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = usuario;
        this.curso = curso;
    }


    public void atualizarDadosTopicos(@Valid DadosTopico dados, Curso curso, Usuario usuario ) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = usuario;
        this.curso = curso;

    }
}
