package br.com.forum.api.controller;

import br.com.forum.api.domain.ValidacaoException;
import br.com.forum.api.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // ==== CADASTRAR USUÁRIO NO BANCO DE DADOS ====
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(
            @RequestBody @Valid DadosCadastroUsuario dados,
            UriComponentsBuilder uriBuilder){

        // Cadastra o usuário com base na validação do body recebido
        var usuario = new Usuario(dados);
        // Salva no banco de dados
        repository.save(usuario);

        // Cria uma url de acesso ao usuário para visualização do mesmo
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        // Retorna os dados de detalhamento do usuário criado
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    // ==== DETALHAR USUÁRIO ====
    @GetMapping("/{id}")
    public ResponseEntity detalharUsuario(@PathVariable Long id){

        // Recupera o usuário do repositório, caso o tenha encontrado
        var usuario = repository.getReferenceById(id);

        // Caso o tenha encontrado, retorna o código 200 e o detalhamento
        // do usuário no body.
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    // ==== DELETAR USUÁRIO ====
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarUsuario(@PathVariable Long id){

        //Recupera a referência do usuário, e o deleta
        var usuario = repository.getReferenceById(id);
        usuario.deletar();

        // Retorna código 204 sem conteúdo
        return ResponseEntity.noContent().build();
    }

    // ==== ALTERAR USUÁRIO ====
    @PatchMapping
    @Transactional
    public ResponseEntity alterarUsuario (@RequestBody @Valid DadosAlterarUsuario dados){

        // Recupera o id do dado a ser alterado da requisição
        Long id = dados.id();

        // Verifica se o id do usuário existe e está ativo
        if (!repository.existsUsuarioByIdAndAtivoTrue(id)){
            throw new ValidacaoException("O usuário não existe no sistema.");
        }

        // Recupera este usuário do banco de dados e o
        // atualiza
        var usuario = repository.getReferenceById(id);
        usuario.atualizar(dados);

        // Retorna as novas informações
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }
}
