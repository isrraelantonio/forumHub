package Challend.Forum.hub.domain.perfil;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Perfil")
@Table(name = "perfis")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")


public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String classificacao;




}
