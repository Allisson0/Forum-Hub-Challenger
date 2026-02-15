package br.com.forum.api.domain.topico;

import br.com.forum.api.domain.ValidacaoException;
import br.com.forum.api.domain.curso.CursoRepository;
import br.com.forum.api.domain.topico.validacoes.ValidaCadastroTopico;
import br.com.forum.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CadastrarTopico {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<ValidaCadastroTopico> validacao;

    // ==== CADASTRO DE TÓPICO ====
    public DadosDetalhamentoTopico cadastrar(DadosCadastroTopico dados) {


        // Para caso o ID do usuário ou do curso seja inválido,
        // retorna uma exception
        if (!usuarioRepository.existsById(dados.idUsuario())) {
            throw new ValidacaoException("Usuário não encontrado.");
        }
        if (!cursoRepository.existsById(dados.idCurso())) {
            throw new ValidacaoException("Curso não encontrado");
        }

        // Realiza todas as validações de tópico.
        validacao.forEach(e -> e.validar(dados));

        // Recupera a referência do autor pelo id
        var autor = usuarioRepository.getReferenceById(dados.idUsuario());

        // Recupera a referência do curso pelo id
        var curso = cursoRepository.getReferenceById(dados.idCurso());

        // Recupera a data local atual
        LocalDate dataCriacao = LocalDate.now();

        // Cria um novo tópico com as informações adquiridas
        var topico = new Topico(
                null, dados.titulo(), dados.mensagem(),
                dataCriacao, "NÃO RESPONDIDO", autor, curso
        );

        // Salva este tópico no banco de dados
        topicoRepository.save(topico);

        // Retorna o detalhamento deste topico
        return new DadosDetalhamentoTopico(topico);

    }
}
