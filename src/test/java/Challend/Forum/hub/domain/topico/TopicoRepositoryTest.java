package Challend.Forum.hub.domain.topico;

import Challend.Forum.hub.domain.curso.Categoria;
import Challend.Forum.hub.domain.curso.Curso;
import Challend.Forum.hub.domain.curso.CursoRepository;
import Challend.Forum.hub.domain.curso.DadosCurso;
import Challend.Forum.hub.domain.usuario.DadosUsuario;
import Challend.Forum.hub.domain.usuario.Usuario;
import Challend.Forum.hub.domain.usuario.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TopicoRepositoryTest {

    @Autowired
    TopicoRepository topicoRepository;


    @Autowired
    private TestEntityManager em;




    @Test
    @DisplayName("deveria retornar null em caso de não háver tópico com o id passado")
    void topicoPeloIdCenario1() {
        criacaoDeTopicos();
        var topico = topicoRepository.topicoPeloId(90l);
        assertThat(topico).isNull();
    }

    @Test
    @DisplayName("deveria retornar um objeto do tipo Topico com o id passado")
    void topicoPeloIdCenario2() {
         var topicoCriado = criacaoDeTopicos();
        var topico = topicoRepository.topicoPeloId(topicoCriado.getId());
        assertThat(topico).isNotEqualTo(null);
    }



    private  Topico criacaoDeTopicos(){
        Usuario usuario = new Usuario(new DadosUsuario("julio amoura","julioamoura@gmail.com", "1233"));
        em.persist(usuario);
        Curso curso = new Curso(new DadosCurso("física", Categoria.BACHARELADO));
        em.persist(curso);
        Topico topicoPronto = new Topico("movimento uniformemente variado", "Descreva o que é?", usuario, curso);
        em.persist(topicoPronto);
        System.out.println(topicoPronto.getId());
        return topicoPronto;


    }

}