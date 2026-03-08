package br.com.forum.api.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Verifica se um usuário existe pelo seu ID
    boolean existsUsuarioByIdAndAtivoTrue(Long id);

    // Procura o login de um usuário pelo seu Email
    // e condição de estar ativo no sistema
    UserDetails findByEmailAndAtivoTrue(String login);

    // Verifica se já existe um usuário com o email
    boolean existsByEmail (String email);
}
