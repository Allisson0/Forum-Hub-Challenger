package br.com.forum.api.domain.topico;

import java.time.LocalDate;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDate dataDeCriacao,
        String autor,
        Long idAutor,
        String curso,
        Long idCurso
) {
    public DadosDetalhamentoTopico(Topico dados){
        this(
                dados.getId(), dados.getTitulo(), dados.getMensagem(),
                dados.getDataCriacao(), dados.getAutor().getNome(),
                dados.getAutor().getId(), dados.getCurso().getNome(),
                dados.getCurso().getId()
        );
    }
}
