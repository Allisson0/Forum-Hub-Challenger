package br.com.forum.api.infra.security;

import br.com.forum.api.domain.ValidacaoException;
import br.com.forum.api.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // CHAVE SECRETA
    @Value("${api.security.token.jwt}")
    private String secret;

    // ==== GERA TOKEN JWT ====
    public String gerarToken(Usuario usuario){

        // Declaração do token
        String token;

        // Geração do token
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withIssuer("API Forum Hub")
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(gerarExpiracao())
                    .sign(algoritmo);

        } catch (JWTCreationException exception){
            throw new ValidacaoException("Erro na criação do token JWT");
        }

        return token;
    }

    // ==== GERAR DATA DE EXPIRAÇÃO DUAS HORAS DEPOIS DA GERAÇÃO DO TOKEN ====
    private Instant gerarExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3:00"));
    }

}
