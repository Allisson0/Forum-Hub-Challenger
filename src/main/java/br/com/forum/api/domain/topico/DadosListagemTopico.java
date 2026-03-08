package br.com.forum.api.domain.topico;

import java.time.format.DateTimeFormatter;

public record DadosListagemTopico(
        String titulo,
        String mensagem,
        String dataCriacao,
        String status,
        String autor,
        String curso
) {

    // ==== CRIA UMA LISTAGEM COM BASE NO TOPICO ====
    public DadosListagemTopico(Topico dados){
        this(
                dados.getTitulo(),
                dados.getMensagem(),
                dados.getDataCriacao().toString(),
                dados.getStatus(),
                dados.getAutor().getNome(),
                dados.getCurso().getNome()
        );
    }

}
