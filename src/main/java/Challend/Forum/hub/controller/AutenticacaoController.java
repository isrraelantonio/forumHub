package Challend.Forum.hub.controller;

import Challend.Forum.hub.domain.usuario.DadosUsuarioLogin;
import Challend.Forum.hub.domain.usuario.Usuario;
import Challend.Forum.hub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@RestController
//@RequestMapping
//@RequiredArgsConstructor
//public class AutenticacaoController {
//
//    private final AuthenticationManager authenticationManager;
//    private final TokenService tokenService;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosUsuarioLogin dados){
//        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
//        var auth = authenticationManager.authenticate(token);
//        System.out.println(auth); // deve imprimir org.springframework.security...
//
//        var jwt = tokenService.gerarToken(auth.getName());
//
//        return ResponseEntity.ok(jwt);
//
//    }
//}


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosUsuarioLogin dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(tokenJWT);
    }
}
