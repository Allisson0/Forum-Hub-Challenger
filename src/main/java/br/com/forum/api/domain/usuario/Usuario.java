package br.com.forum.api.domain.usuario;

import br.com.forum.api.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

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
}
