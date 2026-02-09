alter table topicos modify column status VARCHAR(100) NOT NULL;
update topicos set status = "NÃO RESPONDIDO";