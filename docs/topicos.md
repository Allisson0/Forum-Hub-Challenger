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
	"id": "id",
	"titulo": "titulo",
	"mensagem": "mensagem",
	"dataDeCriacao": "data de criação",
	"status": "status",
	"autor": "Nome do autor",
	"idAutor": "idAutor",
	"curso": "nome do curso",
	"idCurso": "idCurso"
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
	"idAutor": "idAutor",
	"curso": "nome do curso",
	"idCurso": "idCurso"
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
