package br.com.forum.api.controller;

import br.com.forum.api.domain.usuario.DadosLoginUsuario;
import br.com.forum.api.domain.usuario.UsuarioRepository;
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

    // ==== LOGIN DE USUÁRIO CADASTRADO ====
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosLoginUsuario dados) {
        //var tokenAutenticacao = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        //var autenticacao = manager.authenticate(tokenAutenticacao);

        return ResponseEntity.ok().build();
    }


}
