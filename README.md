<div align="center">
  <img src="./docs/imgs/ForumHub.png" alt="Capa do Fórum">

  <h1 id = "topo">:people_hugging: Fórum Hub :people_hugging:</h1>
</div>

<p>
  Projeto criado para aplicar os conhecimentos adquiridos durante o curso sobre Spring Boot com RestAPI. A aplicação representa um contexto de fórum online, com cursos, usuários e tópicos onde perguntas são realizadas.
  Usufrui de persistência de dados com o uso do MySql e Migrations para criação das tabelas. Segurança STATELESS com Spring Security, Validations e uso de Tokens de autenticação JWT. Além de focar na aplicação de boas 
  práticas de negócio, visando uma boa interpretação, manutenção e aplicação do código.
</p>

<div align="center">
  <h1>Sumário 📑</h1>
</div>
<ol>
  <li> <a href="#desc"               > Descrição do projeto </a> </li>
  <li> <a href="#utilizandoAPI"     > Utilizando a API </a> </li>
  <li> <a href="funcionalidades"    > Funcionalidades da aplicação </a> </li>
  <li> <a href="#acesso"            > Acesso ao projeto </a> </li>
  <li> <a href="#license"           > Licença do projeto </a> </li>
  <li> <a href="#conclusao"         > Conclusão </a> </li>
</ol>

<div align="center">
  <h1 id = "desc"> Descrição do projeto 📚</h1>
</div>
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

<div align="center">
  <h1 id = "utilizandoAPI" > 🛠️ Utilizando a API 🛠️</h1>
</div>

<p>
  Para realizar as requisições, recomendamos o uso de alguns auxiliares para requisição, ex.: Insomnia e Postman. <br>
  Algumas requisições, precisam do uso de um token JWT da aplicação. Para consegui-lo, é necessário realizar o login e copiar este token para o campo de autenticação da requisição.<br>
  A maioria delas, precisam de um body contendo um json com o objeto a ser trabalhado.
</p>

<h3>
  Lista de requisições 📖:
</h3>

<ul>
  <li> <a href= "./docs/usuarios.md"> Usuários </a></li>
  <li> <a href= "./docs/cursos.md"  > Cursos </a></li>
  <li> <a href= "./docs/topicos.md" > Tópicos </a></li>
</ul>

<h2>Exemplos de uso no Insomnia: 💻</h2>

<h3>Cadastro de usuário</h3>

<div align= "center">
  <p>Requisição de post para /usuarios</p>
  <img width="603" height="328" alt="image" src="https://github.com/user-attachments/assets/5cdbc35c-832a-4d5a-807b-c55ac3d8785c" />

  <p>Resposta da requisição: </p>
  <img width="596" height="265" alt="image" src="https://github.com/user-attachments/assets/ca4fdd53-bed5-4c5a-9ac8-baad94f2cfbc" />

</div>

<h3>Cadastro de tópico:</h3>


<div align= "center">
  <p>Requisição</p>
  <img width="605" height="409" alt="image" src="https://github.com/user-attachments/assets/9536e245-8a40-4c24-b914-eaf036ed3694" />
</div>


<div align = "center">
  <p>Token de autenticação (None --> Bearer Token</p>
  <img width="591" height="242" alt="image" src="https://github.com/user-attachments/assets/af477a93-cef1-4da8-b8b6-b3d24a5e0a65" />
</div>



<div align= "center">
  <p> Body de retorno quando Created </p>
  <img width="603" height="329" alt="image" src="https://github.com/user-attachments/assets/b1299029-a7ce-4bd1-ae5d-aae726ccd95e" />
</div>


<div align = "center">
  <p>Localização do objeto:</p>
  <img width="595" height="134" alt="image" src="https://github.com/user-attachments/assets/909ba17b-5270-4544-98e2-e566e3dddf66" />
</div>


<div align="center">
  <h1 id = "funcionalidades">🔍 Funcionalidades da aplicação 🔍</h1>
</div>
<ul>
  <li>Não permite o cadastro de usuários com mesmo email (login)</li>
  <li>Não permite o cadastro de tópicos se o usuário estiver inativo</li>
  <li>Valida campos necessários no cadastro, patch e delete</li>
  <li>Necessita de token para autenticação e uso das requisições (exceto cadastro de usuário e login)</li>
  <li>Não permite a duplicidade de tópicos (mesmo nome e descrição)</li>
  <li>Não permite o login de usuários inativos no sistema</li>
</ul>


<div align="center">
  <h1 id="acesso">📂 Acesso ao projeto 📂</h1>
</div>

<h2>Requisitos para o projeto</h2>

<ul>
  <li>Java 25 LTS</li>
  <li>Maven</li>
  <li>MySql</li>
  <li>Configurações de variáveis de ambiente</li>
</ul>

<h2>Variáveis de ambiente 🔧</h2>
<p>
  O projeto necessita da configuração de algumas variavéis de ambiente para o projeto:
</p>

<ul>
  <li>
    <strong>DATA_SOURCE:</strong> sendo este, o caminho para acesso do banco de dados. Ex.: <pre><code class="language-bash">jdbc:mysql://localhost/forum_hub</code></pre>
  </li>
  <li>
    <strong>DB_USERNAME:</strong> este sendo o usuário de uso para o banco de dados. Ex.: root
  </li>
  <li>
    <strong>DB_PASSWORD:</strong> senha do usuário do banco de dados. Criada na criação do banco de dados.
  </li>
  <li>
    <strong>SECRET_TOKEN_JWT:</strong> segredo do token, para  gerações seguras do Token JWT.
  </li>
</ul>


<div align="center">
  <img width="409" height="110" alt="image" src="https://github.com/user-attachments/assets/fab13c69-2b90-4610-a41f-d2d66df322b6" />
</div>

<p>Sendo pois, necessárias para o uso correto da aplicação.</p>



<h2>Banco de dados 🗃️</h2>

<p>
  Também se faz necessária, a criação do banco de dados que estaremos utilizando. Por exemplo, pegando o exemplo de DATA_SOURCE acima, a última parte, é o nome do nosso banco de dados. forum_hub. <br>
  É necessário que o criemos no nosso banco de dados. Ex.: MySql:
</p>

```mysql
CREATE DATABASE forum_hub;
```

<p>
  O restante das tabelas, será criado automaticamente, via Migrations, na primeira inicialização do projeto.
</p>


<h2>Inicializando o projeto 📷</h2>
<p>
  1. Agora, poderemos inicializar o projeto, primeiramente, clonando este repositório:
</p>

```bash
git clone https://github.com/Allisson0/Forum-Hub-Challenger
```

<p>
  2. Depois, abrimos a pasta criada
</p>

```bash
cd ./Forum-Hub-Challenger
```

<p>
  3. E então, inicilizamos o programa ou no Prompt de comando, ou no PowerShell, usando o usuário de produção:
</p>

<p>Prompt de comando/CMD</p>

```bash
mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

<p>PowerShell</p>

```
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

<p>Desta maneira, desde que as variáveis de ambiente estejam codificadas corretamente, a API já estará pronta para uso na sua máquina.</p>

<div align="center">
  <h1 id="license">Licença do projeto:</h1>
</div>
<h2>Autor 🧑</h2>

Criado por **Allisson Silva** para fins didáticos como parte do Challenger LiterAlura.

<h2>Licença</h2>

Este projeto é de uso educacional e segue as diretrizes do Projeto Gutenberg para consumo de dados públicos.


<div align="center">
  <h1 id="conclusao">📈 Conclusão 📈</h1>
</div>
<p>
  Esta aplicação, é um ótimo resultado e forma de praticar os conhecimentos adquiridos durante o curso de Spring Boot com RestAPI. É possível utiliza-lo para validar e testar os conhecimentos adquiridos, além de fomentar
  o conhecimento através de uma prática mais real. O uso de boas práticas de programação, no mundo do mercado de trabalho, é uma ótima prática para facilitar a manutenção de códigos no futuro, além de deixar melhor 
  visível para o programador que irá realizar sua manutenção.
</p>

<a href="#topo">Retornar ao topo</a>
