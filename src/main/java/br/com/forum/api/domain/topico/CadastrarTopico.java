package br.com.forum.api.domain.topico;

import br.com.forum.api.domain.ValidacaoException;
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

    // ==== CADASTRO DE TÓPICO ====
    public DadosDetalhamentoTopico cadastrar(DadosCadastroTopico dados) {


        // Para caso o ID do usuário ou do curso seja inválido,
        // retorna uma exception
        if (!usuarioRepository.existsById(dados.idUsuario())) {
            throw new ValidacaoException("Id de usuário não encontrado.");
        }
        if (!cursoRepository.existsById(dados.idCurso())) {
            throw new ValidacaoException("Id do curso não encontrado");
        }

        // Recupera a referência do autor pelo id
        var autor = usuarioRepository.getReferenceById(dados.idUsuario());

        // Recupera a referência do curso pelo id
        var curso = cursoRepository.getReferenceById(dados.idCurso());

        // Recupera a data local atual
        LocalDate dataCriacao = LocalDate.now();

        // Cria um novo tópico com as informações adquiridas
        var topico = new Topico(
                null, dados.titulo(), dados.mensagem(),
                dataCriacao, true, autor, curso
        );

        // Salva este tópico no banco de dados
        topicoRepository.save(topico);

        // Retorna o detalhamento deste topico
        return new DadosDetalhamentoTopico(topico);

    }
}
