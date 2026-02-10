package br.com.forum.api.controller;

import br.com.forum.api.domain.ValidacaoException;
import br.com.forum.api.domain.topico.*;
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

    // ==== DETALHAMENTO DE TÓPICOS ====
    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id){

        // Verifica se o código Id é válido para referência de tópico
        if (!repository.existsById(id)) {
            throw new ValidacaoException("Id de tópico inexistente.");
        }

        // Se for, salva a referência deste id
        var topico = repository.getReferenceById(id);

        // Retorna código ok 200 + dados do tópico
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    // ==== ATUALIZAR TÓPICOS ====
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody DadosAtualizacaoTopico dados){

        // Pega a referência deste topico pelo id
        var topicoRef = repository.findById(id);

        // Verifica se há algo nesta referência
        if (topicoRef.isEmpty()) {

            // Se não houver, retorna uma exception de validação
            throw new ValidacaoException("Tópico inexistente no banco de dados.");
        }

        // Recupera o tópico da referência Optional
        var topico = topicoRef.get();

        // Atualiza os dados com os dados de atualização.
        topico.atualizar(dados);

        // Retorna ok 200 com os dados atualizados.
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    // ==== DELETAR TÓPICOS ====
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTopico(@PathVariable Long id){

        // Verifica se o repositório exite com este id
        if (!repository.existsById(id)) {
            throw new ValidacaoException("Nenhum tópico existente com este id no banco de dados");
        }

        // SE existe, o deleta do banco de dados
        repository.deleteById(id);

        // Retorna 204 sem conteúdo
        return ResponseEntity.noContent().build();
    }

}
