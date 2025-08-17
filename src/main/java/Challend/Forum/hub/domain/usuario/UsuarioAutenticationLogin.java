package Challend.Forum.hub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;





//@Service
//@RequiredArgsConstructor
//public class UsuarioAutenticationLogin implements UserDetailsService {
//
//    private final UsuarioLoginRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return repository.findByLogin(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
//    }
//}

@Service
public class UsuarioAutenticationLogin implements UserDetailsService {

    @Autowired
    private UsuarioRepository repositoryUsuario;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(repositoryUsuario.findByLogin(username));
        return repositoryUsuario.findByLogin(username);
    }
}
