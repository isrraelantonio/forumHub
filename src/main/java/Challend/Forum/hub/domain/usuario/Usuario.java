package Challend.Forum.hub.domain.usuario;

import Challend.Forum.hub.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;


    // Aqui temos um estabelecimento de relação bidirecional especificando a relação em uma única classe
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfil;

    public Usuario(DadosUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }
}
