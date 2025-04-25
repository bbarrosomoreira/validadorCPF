# 🧪 Receita Federal Mock API

🇧🇷 Versão em português abaixo | 🇺🇸 English version below

---

## 🇺🇸 Receita Federal (Brazilian IRS) Mock API

This is a **mock API** that simulates a small portion of the behavior of the Brazilian Receita Federal — specifically CPF validation — for educational and local testing purposes only.

### 🚀 Features

- Simulates CPF status (ACTIVE / INACTIVE)
- Simulates API instability for specific inputs
- Fast and lightweight Spring Boot application

### ⚙️ Business Rules

The API returns different responses based on the first digit of the CPF:

| CPF Starts With | Response      |
|------------------|----------------|
| `0` to `5`       | `status: ATIVO` (Active) |
| `6` or `7`       | `status: INATIVO` (Inactive) |
| `8`              | Simulates an API error (throws exception) |
| Other values     | Default handling |

> ⚠️ This is a **mock** and does not perform real validations. It is not affiliated with the actual Receita Federal.

### ▶️ How to Run

```bash
# Clone the repository
git clone https://github.com/your-user/mock-receita-api.git
cd mock-receita-api

# Run with Maven
./mvnw spring-boot:run
```

The application will run on `http://localhost:8081`.

---

## 🇧🇷 API Simulada da Receita Federal

Esta é uma **API simulada** que replica parte do comportamento da Receita Federal do Brasil — especificamente a validação de CPF — com fins **exclusivamente educacionais e para testes locais**.

### 🚀 Funcionalidades

- Simula o status do CPF (ATIVO / INATIVO)
- Simula instabilidade da API para determinados CPFs
- Aplicação leve desenvolvida com Spring Boot

### ⚙️ Regras de Negócio

A resposta da API varia conforme o dígito inicial do CPF informado:

| CPF começa com | Resposta       |
|----------------|----------------|
| `0` a `5`      | `status: ATIVO` |
| `6` ou `7`     | `status: INATIVO` |
| `8`            | Simula erro da API (lança exceção) |
| Outros valores | Tratamento padrão |

> ⚠️ Esta é uma **simulação** e **não realiza validações reais**. Não tem relação com a Receita Federal oficial.

### ▶️ Como executar

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/mock-receita-api.git
cd mock-receita-api

# Rode com Maven
./mvnw spring-boot:run
```

A aplicação será iniciada em `http://localhost:8081`.
