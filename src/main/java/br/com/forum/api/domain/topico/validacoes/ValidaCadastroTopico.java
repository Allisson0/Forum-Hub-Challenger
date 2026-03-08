package br.com.forum.api.domain.topico.validacoes;

import br.com.forum.api.domain.topico.DadosCadastroTopico;

public interface ValidaCadastroTopico {
    public void validar(DadosCadastroTopico dados);
}
