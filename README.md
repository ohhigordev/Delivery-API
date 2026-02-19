# 🍔 Delivery API - Java Spring Boot

Este projeto é uma API REST completa para gerenciamento de pedidos de um Delivery, desenvolvida durante um treinamento intensivo de 6 capítulos focado em boas práticas de backend, persistência de dados e regras de negócio complexas.

## 🚀 Tecnologias Utilizadas

* **Java 17** (ou superior)
* **Spring Boot 3**
* **Spring Data JPA** (Persistência)
* **PostgreSQL** (Banco de dados relacional)
* **Lombok** (Produtividade)
* **Jackson** (Serialização JSON)
* **Postman** (Testes de integração)

## 🏗️ Arquitetura e Padrões

O projeto foi estruturado seguindo o padrão de camadas para garantir a separação de responsabilidades:
- **Models:** Entidades JPA que representam o esquema do banco de dados.
- **Repositories:** Interfaces que utilizam Spring Data JPA para comunicação com o Postgres.
- **Services:** Camada de regra de negócio e controle transacional (`@Transactional`).
- **Controllers:** Endpoints REST que gerenciam a entrada de dados via DTOs.

## 🛠️ Funcionalidades Implementadas

### 1. Catálogo de Produtos
- CRUD completo de produtos com persistência no PostgreSQL.
- Uso de `BigDecimal` para precisão financeira.

### 2. Fluxo de Pedidos (O Coração do Sistema)
- Relacionamento **Um-para-Muitos** (`One-to-Many`) entre Pedidos e Itens.
- **Integridade de Preço:** O preço do item é fixado no momento do pedido, protegendo o histórico contra futuras alterações no preço do produto.
- **Cálculo Automático:** O sistema processa o subtotal de cada item e o total geral do pedido.

### 3. Gestão de Status (Máquina de Estados)
- Controle de ciclo de vida do pedido: `PENDING`, `PREPARING`, `OUT_FOR_DELIVERY`, `DELIVERED`, `CANCELED`.
- Atualizações parciais utilizando o método **PATCH**.

### 4. Relatórios Financeiros (Business Intelligence)
- Implementação de consultas customizadas com **JPQL**.
- Endpoint de faturamento total baseado em pedidos entregues.

## 🛡️ Desafios Superados (Showcase Técnico)

Durante o desenvolvimento, implementamos soluções para desafios clássicos de APIs:
- **Recursão Cíclica:** Resolvido o problema de loop infinito na serialização JSON entre `Order` e `OrderItem` utilizando `@JsonIgnore`.
- **Atomicidade:** Uso de transações para garantir que um pedido só seja salvo se todos os seus itens forem processados com sucesso.
- **Diferenciação DTO vs Entidade:** Proteção da camada de dados ao não expor entidades diretamente na requisição.

## 📈 Como Executar

1. Clone o repositório.
2. Configure o seu `application.yml` com as credenciais do seu PostgreSQL local.
3. Certifique-se de que o banco `delivery_db` existe.
4. Execute a aplicação via IntelliJ ou terminal: `./mvnw spring-boot:run`.
5. A API estará disponível em `http://localhost:8080`.

---
Desenvolvido por **Higor** 🚀
