# Java Clean Architecture Example

## Cadastrar cliente

Como: usuário do sistema  
Eu quero: me cadastrar no sistema  
Para que: possa saber meu Score no SERASA

Devemos:
- receber o CPF informado pelo usuário  
- o nome  
- validar a existencia do usuário
- retornar aos seguintes atributos:  

URL a ser criado: GET: /users  
```json
  {
    "name": "Jack",
    "document": "04715476975",
    "errors": [{
        "message": "document is mandatory"
    }]
  }
```

Status para retonar:
- 201 em caso de sucesso
- 422 em caso de dos campos obrigatórios não existirem

## Consultar Score SERASA

Como: usuário do sistema
Eu quero: consultar meu Score do SERASA
Para que: eu saiba minha pontuação de crédito

Devemos:
- receber o CPF informado pelo usuário  
- validar a existencia do usuário
- consultar a integração do SERASA para consultar o Score 
- retornar aos seguintes atributos:  

URL a ser criado: GET: /score/{document}
```json
  {
    "score": {
      "value": 95,
      "user": {
        "name": "Jack",
        "document": "04715476975"
      }   
    },
    "errors": [{
        "message": "document is mandatory"
    },]
  }
```

#### Integração

http://score-mock.getsandbox.com/score/39581190856

## References:
http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
