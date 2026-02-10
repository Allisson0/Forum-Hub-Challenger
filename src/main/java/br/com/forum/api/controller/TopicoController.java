package br.com.forum.api.controller;

import br.com.forum.api.domain.topico.CadastrarTopico;
import br.com.forum.api.domain.topico.DadosCadastroTopico;
import br.com.forum.api.domain.topico.DadosListagemTopico;
import br.com.forum.api.domain.topico.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private CadastrarTopico cadastroTopico;

    @Autowired
    private TopicoRepository repository;

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

    // ==== LISTAGEM DE TODOS OS TÓPICOS ====
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos (
            @PageableDefault(size = 10, sort = {"titulo"}, page = 0) Pageable page){

        // Retorna uma lista dos tópicos ordenados pelo nome
        var paginacao = repository.findAll(page).map(DadosListagemTopico::new);

        // Retorna código ok 200 + as páginas
        return ResponseEntity.ok(paginacao);
    }
}
