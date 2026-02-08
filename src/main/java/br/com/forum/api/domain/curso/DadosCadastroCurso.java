package br.com.forum.api.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroCurso(
        @NotNull
        String nome,
        @NotNull
        Categoria categoria
) {
}
