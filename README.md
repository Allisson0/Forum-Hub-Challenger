<h1>Fórum Hub</h1>
<p>
  Projeto criado para aplicar os conhecimentos adquiridos durante o curso sobre Spring Boot com RestAPI. A aplicação representa um contexto de fórum online, com cursos, usuários e tópicos onde perguntas são realizadas.
  Usufrui de persistência de dados com o uso do MySql e Migrations para criação das tabelas. Segurança STATELESS com Spring Security, Validations e uso de Tokens de autenticação JWT. Além de focar na aplicação de boas 
  práticas de negócio, visando uma boa interpretação, manutenção e aplicação do código.
</p>

<h1>Sumário</h1>
<ol>
  <li> <a href="desc"               > Descrição do projeto </a> </li>
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
  <li> <a href= "#usuarios"> Usuários </a></li>
  <li> <a href= "#cursos"  > Cursos </a></li>
  <li> <a href= "#topícos" > Tópicos </a></li>
</ul>

<h2 id = "usuarios"> Usuários </h2>

```
/usuarios
```

<details>
  <summary>
    <h3>Cadastro</h3>
  </summary>
  <p>
    Para realizar um cadastro de usuário, é necessário informar o nome do usuário, um email válido no padrão de emails, e uma senha com um <strong>mínimo de oito digitos</strong>.
  </p>
  
  ```http
  POST /usuarios
  Content-Type: application/json
  {
    "nome": "Nome do usuário aqui",
    "email": "email.valido@gmail.com",
    "senha": "SuaSenhaValida"
  }
  ```

  <p>
    Retorno sucesso:
  </p>

  ***RESPOSTA:*** 201 Created

  ``` http
    Location: /usuarios/idCriado
    {
      "id": "idDoUsuarioCriado",
      "nome": "Nome do usuário aqui",
      "email": "email.valido@gmail.com"
    }
  ```

<br>

  <details>
  <summary>Resposta a erros:</summary>
  <p><strong>Cenário 1:</strong> cadastro de email duplicado</p>
    
  ***RESPOSTA:*** 400 Bad Request
    
  ```json
  {
    "Email já cadastrado na aplicação."
  }
  ```

  <p><strong>Cenário 2:</strong> email em formato errado </p>

***RESPOSTA:*** 400 Bad Request

```json
[
  {
    "campo:" "email",
    "mensagem": "deve ser um endereço de e-mail bem formado"
  }
]
```

 <p><strong>Cenário 3:</strong> senha inválida</p>

***RESPOSTA:*** 400 Bad Request

```json
[
	{
		"campo": "senha",
		"mensagem": "deve corresponder a \".{8,}$\""
	}
]
```
    
  </details>

</details>

<details>
  <summary>
    <h3>Login</h3>
  </summary>
  <p>
    Para realizar um login, é necessário que a requisição contenha um json com as informações do login do usuário (email) e a sua senha.
  </p>
  
  ```http
  POST /login
  Content-Type: application/json
  {
    "login": "seuemail.validoaqui@gmail.com",
    "senha": "SuaSenhaValida"
  }
  ```

  <p>Resposta para login correto: retorno de token de autenticação válido por duas horas.</p>

  ***RESPOSTA:*** 200 OK

```json
{
  "token": "seuTokenDeAutenticacaoAqui"
}
```


<details>
  <summary>
  Resposta a erros:
  </summary>
   <p><strong>Cenário 1:</strong> Login inválido</p>

***RESPOSTA:*** 403 Forbbiden
  
</details>
</details>

<details>
  <summary><h3>Detalhar</h3></summary>

  <p>
    Detalhes sobre usuários, utilizam da requisição do tipo <strong>GET</strong> com o id do usuário na requisição:
  </p>

```http
GET /usuarios/{id}
Authorization: Bearer {seuTokenAqui}
```

***RESPOSTA:*** 200 OK
```json
{
  "id": "idDoUsuario",
  "nome": "nomeDoUsuario",
  "email": "emailDoUsuario"
}
```
  
</details>


<details>
  <summary><h3>Alterar</h3></summary>

<p>
  Para alterar usuários, é necessários enviar uma requisição PATCH com as informações de id e novas informações a serem alteradas. É importante ressaltar, que o email não pode ser alterado, podendo serem alteradas
  apenas o nome e/ou a senha do usuário.
</p>

```http
PATCH /usuarios
Authorization: Bearer {seuTokenAqui}
Content-Type: application/json
{
  "id": "idDoUsuario",
  "nome": "NovoNome",
  "senha": "NovaSenhaValida"
}
```

***RESPOSTA:*** 200 Ok

```json
    {
      "id": "idDoUsuarioCriado",
      "nome": "Nome do usuário aqui",
      "email": "email.valido@gmail.com"
    }
```

<details>
  <summary>Resposta a erros:</summary>

  <p><strong>Cenário 1:</strong> senha inválida</p>

***RESPOSTA:*** 400 Bad Request

```json
[
	{
		"campo": "senha",
		"mensagem": "deve corresponder a \".{8,}$\""
	}
]
```

  <p><strong>Cenário 2:</strong> campo id inexistente</p>

***RESPOSTA:*** 400 Bad Request

```json
[
	{
		"campo": "id",
		"mensagem": "não deve ser nulo"
	}
]
```
  
</details>
</details>

<details>
  <summary><h3>Deletar</h3></summary>

<p>
  Para deletar usuários, é enviado na requisição, o id do mesmo.
</p>

```http
DELETE /usuarios/{id}
Authorization: Bearer {seuTokenAqui}
Content-Type: application/json
```

***RESPOSTA:*** 204 No Content

  
</details>





<h2>Cursos</h2>

```
/cursos
```

<details>
<summary><h3>Cadastrar</h3></summary>
<p>Para cadastrar um curso, é necessário enviar um POST com seu nome e sua categoria, podendo ser entre: BACKEND, FRONTEND, DEVOPS, CYBERSECURITY, SOFTSKILLS.</p>

```http
POST /cursos
Content-Type: application/json
Authorization: Bearer {SeuTokenAqui}
{
  "nome": "nomeDoCurso",
  "categoria": "CATEGORIA"
}
```

***RESPOSTA:*** 200 OK

```http
Location: /cursos/{id}
{
  "id": "idCriado",
  "nome": "nomeDado",
  "categoria": "CATEGORIA"
}
```

<details>
  <summary>Resposta a erros:</summary>

  <p><strong>Cenário 1:</strong> categoria inválida</p>

***RESPOSTA:*** 400 Bad Request

```json
JSON parse error: Cannot deserialize value of type `br.com.forum.api.domain.curso.Categoria` from String "BACKND": not one of the values accepted for Enum class: [CYBERSECURITY, FRONTEND, SOFTSKILLS, DEVOPS, BACKEND]
```

  <p><strong>Cenário 2:</strong> campo faltante</p>

***RESPOSTA:*** 400 Bad Request

```json
[
	{
		"campo": "campo",
		"mensagem": "não deve ser nulo"
	}
]
```
</details>  
</details>


<details>
<summary><h3>Detalhar</h3></summary>
<p>Para detalhar um curso, é enviado um GET com o id do curso procurado.</p>

```http
GET /cursos/{id}
Content-Type: application/json
Authorization: Bearer {SeuTokenAqui}
```

***RESPOSTA:*** 200 OK

```http
{
  "id": "id",
  "nome": "nomeDado",
  "categoria": "CATEGORIA"
}
```

<details>
  <summary>Resposta a erros:</summary>

  <p><strong>Cenário 1:</strong> curso inexistente com o id</p>

***RESPOSTA:*** 404 Not Found
</details>  
</details>


<details>
  <summary><h3>Alterar</h3></summary>

<p>
  É possível, apenas alterar o nome do curso, enviando uma requisição do tipo PATCH com o id no body da requisição.
</p>

```http
PATCH /cursos
Authorization: Bearer {seuTokenAqui}
Content-Type: application/json
{
  "nome": "NovoNomeDoCurso",
}
```

***RESPOSTA:*** 200 Ok

```json
    {
      "id": "id",
      "nome": "Novo Nome Aqui",
      "categoria": "CATEGORIA"
    }
```

<details>
  <summary>Resposta a erros:</summary>

  <p><strong>Cenário 1:</strong> campo nome inexistente</p>

***RESPOSTA:*** 400 Bad Request

```json
[
	{
		"campo": "nome",
		"mensagem": "não deve estar em branco"
	}
]
```

  <p><strong>Cenário 2:</strong> campo id inexistente</p>

***RESPOSTA:*** 400 Bad Request

```json
[
	{
		"campo": "id",
		"mensagem": "não deve ser nulo"
	}
]
```
  
</details>
</details>


<details>
<summary>
  <h3>Listar</h3>
</summary>

<p>Para listar cursos, é necessário enviar um GET para o escopo da requisição dos cursos. A lista, por padrão, virá com 10 objetos, ordenados pelo nome.</p>

```http
GET /cursos
Authorization: Bearer {seuTokenAqui}
```

***RESPOSTA:*** 200 Ok

```json
{
	"content": [
			Cursos disponíveis aqui
	],
	"empty": false,
	"first": true,
	"last": true,
	"number": 0,
	"numberOfElements": 1,
	"pageable": {
		"offset": 0,
		"pageNumber": 0,
		"pageSize": 10,
		"paged": true,
		"sort": {
			"empty": false,
			"sorted": true,
			"unsorted": false
		},
		"unpaged": false
	},
	"size": 10,
	"sort": {
		"empty": false,
		"sorted": true,
		"unsorted": false
	},
	"totalElements": 1,
	"totalPages": 1
}
```
  
</details>




<h2 id = "topicos"> Tópicos </h2>

```
/topicos
```

<details>
  <summary>
    <h3>Cadastro</h3>
  </summary>
  <p>
    Para realizar um cadastro de tópicos, é necessário informar o título do tópico, a mensagem do tópico, o id do autor e o id do curso ao qual o tópico foi destinado.
  </p>
  
  ```http
POST /topicos
Content-Type: application/json
Authorization: Bearer {seuTokenAqui}
{
	"titulo": "SeuTitulo",
	"mensagem": "Sua mensagem de até 500 caracteres",
	"idUsuario": id do autor,
	"idCurso": id do curso pertencente
}
  ```

  <p>
    Retorno sucesso:
  </p>

  ***RESPOSTA:*** 201 Created

  ``` http
Location: /topicos/idCriado
{
	"id": id do tópico,
	"titulo": "SeuTitulo",
	"mensagem": "Sua mensagem de até 500 caracteres",
	"dataDeCriacao": "2026-02-15",
	"status": "NÃO RESPONDIDO",
	"autor": "Nome do autor",
	"idAutor": id do autor,
	"curso": "Curso pertencente",
	"idCurso": id do curso
}
  ```

<br>

  <details>
  <summary>Resposta a erros:</summary>
  <p><strong>Cenário 1:</strong> cadastro de tópico duplicado</p>
    
  ***RESPOSTA:*** 400 Bad Request
    
  ```json
  Tópico existente já encontrado.
  ```

  <p><strong>Cenário 2:</strong> campo faltante </p>

***RESPOSTA:*** 400 Bad Request

```json
[
  {
    "campo:" "campo",
    "mensagem": "campo não deve ser nulo"
  }
]
```

 <p><strong>Cenário 3:</strong> id ou curso inexistente</p>

***RESPOSTA:*** 400 Bad Request

```json
usuário/curso não encontrado
```

 <p><strong>Cenário 4:</strong> usuário inativo no sistema</p>

***RESPOSTA:*** 400 Bad Request

```json
Usuário inativo no sistema.
```
    
  </details>

</details>

<details>
  <summary><h3>Detalhar</h3></summary>

  <p>
    Detalhes sobre tópicos, utilizam da requisição do tipo <strong>GET</strong> com o id do tópico na requisição:
  </p>

```http
GET /topicos/{id}
Authorization: Bearer {seuTokenAqui}
```

***RESPOSTA:*** 200 OK
```json
{
	"id": id,
	"titulo": "titulo",
	"mensagem": "mensagem",
	"dataDeCriacao": "data de criação",
	"status": "status",
	"autor": "Nome do autor",
	"idAutor": idAutor,
	"curso": "nome do curso",
	"idCurso": idCurso
}
```
  
</details>


<details>
  <summary><h3>Alterar</h3></summary>

<p>
  Para alterar tópicos, é necessário enviar um json com o id, e os campos a serem alterados. Podendo ser alterados os campos de titulo, mensagem e/ou status.
</p>

```http
PATCH /topicos
Authorization: Bearer {seuTokenAqui}
Content-Type: application/json
{
	"id": id,
	"titulo": "novoTitulo",
	"mensagem": "novaMensagem",
	"status": "NovoStatus"
}
```

***RESPOSTA:*** 200 Ok

```json
{
	"id": id,
	"titulo": "titulo",
	"mensagem": "mensagem",
	"dataDeCriacao": "data de criação",
	"status": "status",
	"autor": "Nome do autor",
	"idAutor": idAutor,
	"curso": "nome do curso",
	"idCurso": idCurso
}
```

<details>
  <summary>Respostas a erros:</summary>
  <p><strong>Cenário 1:</strong> campo id inexistente</p>

***RESPOSTA:*** 400 Bad Request

```json
[
	{
		"campo": "id",
		"mensagem": "não deve ser nulo"
	}
]
```
  
</details>
</details>



<details>
  <summary><h3>Deletar</h3></summary>

<p>
  Para deletar tópicos, é enviado na requisição, o id do mesmo.
</p>

```http
DELETE /topicos/{id}
Authorization: Bearer {seuTokenAqui}
Content-Type: application/json
```

***RESPOSTA:*** 204 No Content

<details>
  <summary>Respostas a erros:</summary>
  <p><strong>Cenário 1:</strong> id de tópico inexistente</p>

***RESPOSTA:*** 400 Bad Request

```json
Nenhum tópico existente com este id no banco de dados
```
  
</details>
  
</details>
