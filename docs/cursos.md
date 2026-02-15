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
			"Cursos disponíveis aqui"
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