package br.com.forum.api.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // ==== VERIFICA SE EXISTE MENSAGEM E TITULO IGUAL NO BANCO ====
    boolean existsByMensagemAndTitulo(String mensagem, String titulo);
}
