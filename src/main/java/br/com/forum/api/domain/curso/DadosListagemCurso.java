package br.com.forum.api.domain.curso;

public record DadosListagemCurso(
        String nome,
        String categoria
) {
    public DadosListagemCurso (Curso dados){
        this(dados.getNome(), dados.getCategoria().toString());
    }
}
