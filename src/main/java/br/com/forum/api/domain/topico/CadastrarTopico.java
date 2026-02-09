package br.com.forum.api.domain.topico;

import br.com.forum.api.domain.curso.CursoRepository;
import br.com.forum.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CadastrarTopico {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico cadastrar(DadosCadastroTopico dados) {

        LocalDate dataCriacao = LocalDate.now();

        var autor = usuarioRepository.getReferenceById(dados.idUsuario());

        var curso = cursoRepository.getReferenceById(dados.idCurso());

        var topico = new Topico(
                null, dados.titulo(), dados.mensagem(),
                dataCriacao, true, autor, curso
        );

        topicoRepository.save(topico);

        return topico;

    }
}
