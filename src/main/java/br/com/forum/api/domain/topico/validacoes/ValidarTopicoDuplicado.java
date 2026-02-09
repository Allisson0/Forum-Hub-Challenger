package br.com.forum.api.domain.topico.validacoes;

import br.com.forum.api.domain.ValidacaoException;
import br.com.forum.api.domain.topico.DadosCadastroTopico;
import br.com.forum.api.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarTopicoDuplicado implements ValidaCadastroTopico {

    @Autowired
    private TopicoRepository repository;

    @Override
    public void validar(DadosCadastroTopico dados) {
        if (repository.existsByMensagemAndTitulo(dados.mensagem(), dados.titulo())){
            throw new ValidacaoException("""
                    Tópico existente já encontrado.""");
        }
    }
}
