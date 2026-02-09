package br.com.forum.api.controller;

import br.com.forum.api.domain.topico.CadastrarTopico;
import br.com.forum.api.domain.topico.DadosCadastroTopico;
import br.com.forum.api.domain.topico.DadosDetalhamentoTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private CadastrarTopico cadastroTopico;

    // ==== POST DE CADASTRO DE TÓPICOS ====
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTopico(
            @RequestBody @Valid DadosCadastroTopico dados,
            UriComponentsBuilder uriBuilder){

        // Valida a criação de um tópico
        var topico = cadastroTopico.cadastrar(dados);

        // Cria uma url de acesso dos detalhes do tópico
        var uri = uriBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.id()).toUri();

        // Retorna a url de acesso e os dados do topico.
        return ResponseEntity.created(uri).body(topico);
    }

}
