package br.com.forum.api.controller;

import br.com.forum.api.domain.usuario.DadosLoginUsuario;
import br.com.forum.api.domain.usuario.Usuario;
import br.com.forum.api.domain.usuario.UsuarioRepository;
import br.com.forum.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    // ==== LOGIN DE USUÁRIO CADASTRADO ====
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosLoginUsuario dados) {

        // Transforma num objeto de autenticação para efetuar o login
        var tokenAutenticacao = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        // Autentica os dados enviados, com base no repositório da aplicação
        var autenticacao = manager.authenticate(tokenAutenticacao);

        // Gera o Token JWT com os dados de usuário
        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(tokenJWT);
    }


}
