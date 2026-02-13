package br.com.forum.api.domain.usuario;

import br.com.forum.api.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Topico> topicosDoUsuario = new ArrayList<>();

    private boolean ativo;

    private static final BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();

    public Usuario(DadosCadastroUsuario dados){
        this.nome = dados.nome();

        this.email = dados.email();

        // Cria uma senha encriptada para salvar no banco de dados
        this.senha = gerarSenhaHash(dados.senha());

        this.ativo = true;
    }

    // ==== ENCRIPTADOR SENHA ====
    private String gerarSenhaHash(String senha){
        return encriptador.encode(senha);
    }

    public void deletar() {
        this.ativo = false;
    }

    // RETORNO DE ROLES DO USUÁRIO
    // Atualmente: retorno de autoridade garantida simples
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // RETORNO DO CAMPO DA SENHA DO USUÁRIO
    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    // RETORNO DO LOGIN DO USUÁRIO (pelo email)
    @Override
    public String getUsername() {
        return email;
    }

    // ABAIXO, CONFIGURAÇÕES DE PADRÃO DE RETORNO TRUE
    // PARA O PROJETO DE AUTENTICAÇÃO SIMPLES

    @Override
    public boolean isAccountNonExpired() {
        //return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
        //return UserDetails.super.isEnabled();
        return true;
    }
}
