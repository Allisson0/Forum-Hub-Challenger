package br.com.forum.api.domain.topico;

public record DadosCadastroTopico(
        String titulo,
        String mensagem,
        Long idUsuario,
        Long idCurso
) {
}
