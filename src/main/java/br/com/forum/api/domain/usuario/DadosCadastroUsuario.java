package br.com.forum.api.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuario(
        @NotNull
        String nome,
        @NotNull @Email
        String email,
        //Minimo de 8 dígitos quaisquer
        @NotNull @Pattern(regexp = ".{8,}$")
        String senha
) {
}
