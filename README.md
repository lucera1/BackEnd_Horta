# 🌱 HORTA DELIVERY – Sistema Completo (Back-end + Front-end)

Bem-vindo ao Horta Delivery, um sistema completo de e-commerce de hortaliças com:

✔ Back-end em Spring Boot + JWT
✔ Front-end em HTML/CSS/JS
✔ Autenticação, listagem de produtos, carrinho e pedidos
✔ Integração total via Fetch API

# Diagrama de classe
![Diagrama de classes](./assets/image-1.png)
## 📸 IMAGENS DO PROJETO

## Tela de login

![TelaLogin](./assets/Login.png)

## Tela de criar conta

![TelaLogin](./assets/registrar.png)

## Cards de produtos carregados da API

![TelaInicial](./assets/tela%20inicial.png)

## Tela de carrinho

![TelaPedidos](./assets/carrinho.png)

## Tela de gerenciamento de pedidos

![TelaPedidos](./assets/tela%20pedido.png)

## Tela de gerenciamento de Produto

![TelaDeProduto](./assets/tela%20de%20gerenciamento%20de%20produtos.png)

## Tela de novo produto

![TelaDeProduto](./assets/tela%20de%20criar%20produto.png)


## Tela de gerenciamento de usuarios

![TelaUsuario](./assets/TELA%20DE%20GERENCIAMENTO%20DE%20USUARIO.png)

## Tela de criar novo usuario

![TelaUsuario](./assets/tela%20de%20cadastrar%20usuario.png)


## Tela de sobre

![Sobre](./assets/TELA%20SOBRE%20.png)

## Login no Postman

![LoginPostman](./assets/login%20postman.png)


## Listagem de produtos

![Produtos](./assets/produtos%20findall.png)

## Listagem de pedidos

![Pedidos](./assets/listagem%20de%20pedido.png)

## 🛠 Tecnologias Utilizadas
🔵 Back-end

Java 17+

Spring Boot 3

Spring Security + JWT

Spring Data JPA

H2 Database / MySQL

Lombok

🟢 Front-end

HTML5

CSS3 + Bootstrap 5

JavaScript (ES Modules)

Fetch API

LocalStorage para JWT

## 🔐 Autenticação — JWT
🔸 Endpoint de Login:

POST http://localhost:8080/auth/login

### Body: 
{
  "username": "gui@gmail.com",
  "password": "123"
}

### Resposta:
 {
  "token": "eyJh..."
}

### Token salvo no front:

localStorage.setItem("jwtToken", token);

# 🥬 Endpoints – Produto

| Método | Rota            | Função             |
| ------ | --------------- | ------------------ |
| GET    | `/produto`      | Lista produtos     |
| GET    | `/produto/{id}` | Retorna um produto |
| POST   | `/produto`      | Cria produto       |
| PUT    | `/produto/{id}` | Edita produto      |
| DELETE | `/produto/{id}` | Exclui produto     |

![Produto](./assets/find%20all%20produtos.png)

# 🛒 Endpoints – Pedido
### 🔸 Fluxo completo do pedido:

Criar pedido

Preparar

Entregar

Cancelar

## 📑 Endpoints:

| Método | Rota                    | Descrição            |
| ------ | ----------------------- | -------------------- |
| GET    | `/pedido`               | Lista todos          |
| GET    | `/pedido/{id}`          | Detalhes             |
| POST   | `/pedido`               | Cria                 |
| PUT    | `/pedido/{id}`          | Atualiza             |
| DELETE | `/pedido/{id}`          | Exclui               |
| PUT    | `/pedido/{id}/preparar` | Muda para PREPARANDO |
| PUT    | `/pedido/{id}/entregar` | Muda para ENTREGUE   |
| PUT    | `/pedido/{id}/cancelar` | Muda para CANCELADO  |

![Pedido](./assets/find%20all%20pedidos.png)

# 🌐 Integração Front-end → Back-end

### Exemplo de consumo da API /produto:

![ListarProduto](./assets/codigo%20listar%20produto.png)

# 🧪 Testes no Postman

Checklist:

✔ Login (gera token)
✔ GET /produto
✔ POST /pedido
✔ PUT /pedido/{id}/preparar
✔ PUT /pedido/{id}/entregar

# ▶ Como Rodar o Projeto
🚀 Back-end 

mvn spring-boot:run

Acessar: http://localhost:8080


# 🌐 Front-end 

npx serve

Acessar: http://localhost:3000

🔒 O sistema redireciona automaticamente para login.html se não houver token

# 🔧 Banco de Dados

H2 Console:
http://localhost:8080/h2-console

![BancoDeDadosH2](./assets/Banco%20de%20dados%20H2.png)

# ✨ Funcionalidades Implementadas

✔ Login com JWT
✔ Lista produtos do back-end
✔ Carrinho com LocalStorage
✔ Finaliza pedido enviando para API
✔ Fluxo do pedido (preparar, entregar, cancelar)
✔ Proteção de páginas (não entra sem login)
