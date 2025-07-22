package Challend.Forum.hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
//    List<Topico> findByEstadoDoTopicoTrue();

    Page<Topico> findByEstadoDoTopicoTrue(Pageable paginacao);


    @Query("""
            SELECT t FROM Topico t
            WHERE
            t.estadoDoTopico = true AND
            t.curso.id = (
                SELECT c.id FROM Curso c WHERE c.nome = :nomeCurso
            )
            """)
    Page<Topico> topicoPeloCurso(Pageable paginacao, String nomeCurso);


    @Query(value = """
             SELECT t.* FROM topicos t
                 JOIN cursos c ON t.curso_id = c.id
                 WHERE t.estado_do_topico = true
                 AND YEAR(t.data_criacao) = :ano
                 AND c.nome COLLATE utf8mb4_general_ci = :nomeCurso
                  ORDER BY t.data_criacao 
            """, nativeQuery = true)
    Page<Topico> topicoPeloano(Pageable paginacao,String nomeCurso, String ano);
}
