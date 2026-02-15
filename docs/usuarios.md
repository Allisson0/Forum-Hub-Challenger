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
  "Email já cadastrado na aplicação."
  ```

  <p><strong>Cenário 2:</strong> email em formato errado </p>

***RESPOSTA:*** 400 Bad Request

```json
[
  {
    "campo": "email",
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


<h5>
    <a href="../README.md">Retornar ao menu principal.</a>
</h5>