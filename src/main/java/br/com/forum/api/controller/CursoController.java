package br.com.forum.api.controller;

import br.com.forum.api.domain.curso.Curso;
import br.com.forum.api.domain.curso.CursoRepository;
import br.com.forum.api.domain.curso.DadosCadastroCurso;
import br.com.forum.api.domain.curso.DadosDetalhamentoCurso;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    // ==== CRIAR CURSO ====
    @PostMapping
    @Transactional
    public ResponseEntity criarCurso(
            @RequestBody @Valid DadosCadastroCurso dados
            , UriComponentsBuilder uriBuilder
            ){
        // Recupera as informações do body do curso, e o salva
        var curso = new Curso(dados);
        repository.save(curso);

        // Cria um caminho para visualiação do curso
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();

        // Retorna código 204 com o caminho para visualiação e os detalhes do curso
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCurso(curso));
    }

    // ==== DETALHAR CURSO ====
    @GetMapping("/{id}")
    public ResponseEntity detalharCurso(@PathVariable Long id){

        // Pega a referência do id, e retorna um código 200 com os detalhes do curso
        var curso = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

}
