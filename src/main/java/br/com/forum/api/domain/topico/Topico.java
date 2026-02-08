package br.com.forum.api.domain.topico;

import br.com.forum.api.domain.curso.Curso;
import br.com.forum.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;
    private String mensagem;
    private LocalDate dataCriacao;
    private boolean status;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;


    //private List<Resposta> respostas;
}
