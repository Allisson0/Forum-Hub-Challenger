create table usuarios(
    id bigint not null auto increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(255) not null,

    primary key(id)
);