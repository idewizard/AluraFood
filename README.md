# 🍔 Alura Food — Microsserviços com Java e Spring

Projeto desenvolvido durante o curso [**Microsserviços na prática: implementando com Java e Spring**](https://cursos.alura.com.br/course/microsservicos-implementando-java-spring) da [Alura](https://www.alura.com.br), que ensina como migrar de uma arquitetura monolítica para microsserviços utilizando ferramentas como **Spring Boot**, **Spring Cloud Eureka**, **Feign** e **Spring Cloud Gateway**.



## 📐 Arquitetura

O sistema é composto por três microsserviços principais:

- **Gateway API**: ponto de entrada da aplicação, usando Spring Cloud Gateway para roteamento.
- **Pedidos API (`pedidos-ms`)**: responsável pela criação e listagem de pedidos.
- **Pagamentos API (`pagamentos-ms`)**: gerencia os pagamentos, com operações de cadastro, consulta, atualização e exclusão.

Todos os serviços se registram no **Eureka Server**, permitindo descoberta dinâmica e roteamento automático pelo Gateway.

---

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Cloud Gateway
- Spring Cloud Eureka
- Spring Data JPA
- Mysql Database
- Maven
- Lombok

---

## 📦 Endpoints e Exemplos

### 🔹 1. Criar um novo pedido

Cria um novo pedido com múltiplos itens.

**Requisição:**
```
POST /pedidos-ms/pedidos
```

**Payload:**
```json
{
  "itens": [
    {
      "quantidade": 10,
      "descrição": "Coca-cola"
    },
    {
      "quantidade": 5,
      "descrição": "Mc Chicken"
    }
  ]
}
```

**Exemplo com `curl`:**
```bash
curl -X POST http://localhost:8082/pedidos-ms/pedidos \
  -H "Content-Type: application/json" \
  -d '{
    "itens": [
      { "quantidade": 10, "descrição": "Coca-cola" },
      { "quantidade": 5, "descrição": "Mc Chicken" }
    ]
  }'
```

---

### 🔹 2. Listar todos os pedidos

Recupera todos os pedidos registrados no sistema.

**Requisição:**
```
GET /pedidos-ms/pedidos
```

**Exemplo com `curl`:**
```bash
curl http://localhost:8082/pedidos-ms/pedidos
```

---

### 🔸 3. Criar um pagamento

Registra um novo pagamento vinculado a um pedido.

**Requisição:**
```
POST /pagamentos-ms/pagamentos
```

**Payload:**
```json
{
  "valor": 500,
  "nome": "Jacqueline",
  "numero": "12345678",
  "expiracao": "10/29",
  "codigo": "123",
  "pedidoId": 1,
  "formaDePagamentoId": 1
}
```

**Exemplo com `curl`:**
```bash
curl -X POST http://localhost:8082/pagamentos-ms/pagamentos \
  -H "Content-Type: application/json" \
  -d '{
    "valor": 500,
    "nome": "Jacqueline",
    "numero": "12345678",
    "expiracao": "10/29",
    "codigo": "123",
    "pedidoId": 1,
    "formaDePagamentoId": 1
  }'
```

---

### 🔸 4. Consultar um pagamento

Busca os detalhes de um pagamento específico.

**Requisição:**
```
GET /pagamentos-ms/pagamentos/{id}
```

**Exemplo:**
```bash
curl http://localhost:8082/pagamentos-ms/pagamentos/2
```

---

### 🔸 5. Atualizar um pagamento

Atualiza os dados de um pagamento existente.

**Requisição:**
```
PUT /pagamentos-ms/pagamentos/{id}
```

**Payload:**
```json
{
  "valor": 900,
  "nome": "Rodrigo",
  "numero": "12345678",
  "expiracao": "10/29",
  "codigo": "123",
  "pedidoId": 1,
  "formaDePagamentoId": 1,
  "status": "CRIADO"
}
```

**Exemplo com `curl`:**
```bash
curl -X PUT http://localhost:8082/pagamentos-ms/pagamentos/2 \
  -H "Content-Type: application/json" \
  -d '{
    "valor": 900,
    "nome": "Rodrigo",
    "numero": "12345678",
    "expiracao": "10/29",
    "codigo": "123",
    "pedidoId": 1,
    "formaDePagamentoId": 1,
    "status": "CRIADO"
  }'
```

---

### 🔸 6. Deletar um pagamento

Remove um pagamento do sistema.

**Requisição:**
```
DELETE /pagamentos-ms/pagamentos/{id}
```

**Exemplo com `curl`:**
```bash
curl -X DELETE http://localhost:8082/pagamentos-ms/pagamentos/1
```

---

## 🔧 Executando o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/idewizard/AluraFood.git
   cd alura-food
   ```

2. Suba os serviços na seguinte ordem:
   - **Eureka Server**
   - **Pedidos API**
   - **Pagamentos API**
   - **Gateway**

3. Use os exemplos de requisições acima para interagir com a aplicação (via Postman, Insomnia ou terminal).

---

## 📌 Observações

- O roteamento via Gateway acontece utilizando o nome dos serviços registrados no Eureka, como `pedidos-ms` e `pagamentos-ms`.

---

## 👨‍💻 Autor

Projeto realizado como parte dos estudos no curso da Alura.  
GitHub: [Andre Sousa](https://github.com/idewizard)

---
