package br.com.forum.api.domain.topico;

public record DadosAtualizacaoTopico(
        String titulo,
        String mensagem,
        String status
) {
}
