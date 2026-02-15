<h1>Fórum Hub</h1>
<p>
  Projeto criado para aplicar os conhecimentos adquiridos durante o curso sobre Spring Boot com RestAPI. A aplicação representa um contexto de fórum online, com cursos, usuários e tópicos onde perguntas são realizadas.
  Usufrui de persistência de dados com o uso do MySql e Migrations para criação das tabelas. Segurança STATELESS com Spring Security, Validations e uso de Tokens de autenticação JWT. Além de focar na aplicação de boas 
  práticas de negócio, visando uma boa interpretação, manutenção e aplicação do código.
</p>

<h1>Sumário</h1>
<ol>
  <li> <a href="#desc"               > Descrição do projeto </a> </li>
  <li> <a href="#utilizandoAPI"     > Utilizando a API </a> </li>
  <li> <a href="funcionalidades"    > Funcionalidades da aplicação </a> </li>
  <li> <a href="#acesso"            > Acesso ao projeto </a> </li>
  <li> <a href="#license"           > Licença do projeto </a> </li>
  <li> <a href="#conclusao"         > Conclusão </a> </li>
</ol>

<h1 id = "desc"> Descrição do projeto </h1>
<p>
  O projeto é um Forum para usuários cadastrados poderem adicionar tópicos de perguntas sobre cursos cadastrados na plataforma. O mesmo permite a criação de usuários, inativação e alteração de nome e senha do usuário.
  As senhas são sempre salvas no banco de dados, encriptadas por um algoritmo hash, sendo convertidas na criação de um novo usuário. <br>
  É possível também, criar, listar e alterar cursos na aplicação, desde que o usuário esteja logado (via token de autenticação). Além de claro, criar os tópicos com título, mensagem, usuário responsável pelo tópico, curso
  do tópico e situação (se foi ou não resolvido/respondido).
  <br>
  <br>
  É importante ressaltar, que as requisições do usuário, para testes, podem ser feitas na porta:
</p>

``` cmd
http://localhost:8080/  
```

<h1 id = "utilizandoAPI" > Utilizando a API </h1>

<p>
  Para realizar as requisições, recomendamos o uso de alguns auxiliares para requisição, ex.: Insomnia e Postman. <br>
  Algumas requisições, precisam do uso de um token JWT da aplicação. Para consegui-lo, é necessário realizar o login e copiar este token para o campo de autenticação da requisição.<br>
  A maioria delas, precisam de um body contendo um json com o objeto a ser trabalhado.
</p>

<h3>
  Lista de requisições:
</h3>

<ul>
  <li> <a href= "./docs/usuarios.md"> Usuários </a></li>
  <li> <a href= "./docs/cursos.md"  > Cursos </a></li>
  <li> <a href= "./docs/topicos.md" > Tópicos </a></li>
</ul>





