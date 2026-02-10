package br.com.forum.api.domain.topico.validacoes;

import br.com.forum.api.domain.ValidacaoException;
import br.com.forum.api.domain.topico.DadosCadastroTopico;
import br.com.forum.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarUsuarioAtivo implements ValidaCadastroTopico{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DadosCadastroTopico dados) {
        if (!usuarioRepository.existsUsuarioByIdAndAtivoTrue(dados.idUsuario())) {
            throw new ValidacaoException("Usuário inativo no sistema.");
        }
    }
}
