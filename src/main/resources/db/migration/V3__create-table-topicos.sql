create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensagem varchar(500) not null unique,
    data_criacao date not null,
    status tinyint not null,
    usuario_id bigint not null,
    curso_id bigint not null,

    primary key(id),

    constraint fk_topicos_autor_id foreign key(usuario_id) references usuarios(id),
    constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id)
);