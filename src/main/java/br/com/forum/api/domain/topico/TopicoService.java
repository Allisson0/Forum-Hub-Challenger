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
public class TopicoService {

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

    // ==== ATUALIZAR TOPICOS ====
    public DadosDetalhamentoTopico atualizarTopico(DadosAtualizacaoTopico dados) {

        // Recupera o id do body da requisição
        var id = dados.id();
        // Pega a referência deste topico pelo id
        var topicoRef = topicoRepository.findById(id);

        // Verifica se há algo nesta referência
        if (topicoRef.isEmpty()) {
            // Se não houver, retorna uma exception de validação
            throw new ValidacaoException("Tópico inexistente no banco de dados.");
        }

        // Recupera o tópico da referência Optional
        var topico = topicoRef.get();

        // Criação de objeto tópico para validação de novos dados
        var topicoValidar = new Topico(null, topico.getTitulo(), topico.getMensagem(), null, null, null, null);
        topicoValidar.atualizar(dados);

        // Verifica se a mensagem e o título da nova atualização, já não pertecem à um titulo especifico
        // Críterio para duplicidade: mesmo título e mesma mensagem
        if (topicoRepository.existsByMensagemAndTitulo(topicoValidar.getMensagem(), topicoValidar.getTitulo())) {
            throw new ValidacaoException("Tópico já existente com mesmo nome e mensagem no banco de dados.");
        }

        // Atualiza os dados com os dados de atualização.
        topico.atualizar(dados);

        // Retorna ok 200 com os dados atualizados.
        return new DadosDetalhamentoTopico(topico);
    }
}
