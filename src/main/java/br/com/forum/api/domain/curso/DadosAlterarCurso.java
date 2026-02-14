package br.com.forum.api.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosAlterarCurso(
        @NotBlank
        Long id,

        @NotBlank
        String nome
) {
}
