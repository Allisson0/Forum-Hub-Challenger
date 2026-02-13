package br.com.forum.api.infra.security;

import br.com.forum.api.domain.ValidacaoException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// REALIZA O FILTRO UMA VEZ, POR REQUISIÇÃO
@Component
public class SecurityFilter extends OncePerRequestFilter {

    // ==== PARA CADA REQUISIÇÃO FAÇA ESTE FILTRO ====
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Recupera o token, a partir da requisição
        var tokenJwt = recuperarToken(request);

        // Chama o próximo filtro da requisição
        filterChain.doFilter(request, response);

    }


    // ==== RECUPERAR TOKEN DA REQUISIÇÃO ====
    private String recuperarToken(HttpServletRequest http){
        // Recupera o token do cabeçalho de authorization
        var cabecalhoDeAutorizacao = http.getHeader("Authorization");

        // SE não vier nada, chama uma exception de validação
        if (cabecalhoDeAutorizacao == null) {
            throw new ValidacaoException("Token JWT não enviado no cabeçalho de autorização.");
        }

        // Se houver, retorna ele
        return cabecalhoDeAutorizacao;
    }
}
