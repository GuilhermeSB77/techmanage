# **User Management API** 🚀

![Java](https://img.shields.io/badge/java-17-blue)  
![Spring Boot](https://img.shields.io/badge/spring%20boot-3.4.4-brightgreen)  
![MySQL](https://img.shields.io/badge/mysql-8.0-blue)  
![Insomnia](https://img.shields.io/badge/insomnia-testing-purple)


API RESTful para gerenciamento de usuários com **CRUD completo**, **validações**, **MySQL** e suporte a **Docker**. Ideal para testes com Insomnia/Postman.

---

## **📌 Índice**
1. [Pré-requisitos](#-pré-requisitos)
2. [Configuração do Banco de Dados](#-configuração-do-banco-de-dados)
3. [Execução Local](#-execução-local)
4. [Testando a API (Insomnia)](#-testando-a-api-insomnia-ou-postman)
5. [Endpoints](#-endpoints)
6. [Tratamento de Erros](#-tratamento-de-erros)

---

## **⚙️ Pré-requisitos**
- **Java 17+**
- **MySQL 8.0+** (ou Docker, se preferir rodar containerizado)
- **Maven 3.6+**
- **Insomnia/Postman** (para testar endpoints)
- **Docker** (opcional, para execução via containers)

---

## **📊 Configuração do Banco de Dados**

### **1. MySQL Local**
Crie o banco e a tabela manualmente:
```sql
CREATE DATABASE techmanage_db;
USE techmanage_db;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL,
    user_type ENUM('ADMIN', 'EDITOR', 'VIEWER') NOT NULL
);
```

### **2. Configure `application.properties` para o seu DB**
```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/techmanage_db?serverTimezone=UTC
spring.datasource.username=root  -- substitua pelo seu usuário do DB
spring.datasource.password=senha -- substitua pela sua senha do DB
server.port=8080
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jackson.date-format=yyyy-MM-dd
spring.jackson.time-zone=UTC
```

## **💻 Execução Local**

1. **Clone o repositório**
   ```bash
   git clone https://github.com/GuilhermeSB77/techmanage.git
   cd techmanage
   ```
3. **Teste a aplicação**
   ```bash
   mvn clean instal
   mvn test
   ```

4. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

5. **Acesse a API**
    - Disponível em: `http://localhost:8080/api/users`
---

## **🛠️ Testando a API (Insomnia ou Postman)**

### **1. Importe a Coleção**
- Baixe o arquivo [`Insomniatechmanage`](./Insomniatechmanage) (disponível no repositório).
- No Insomnia: **Import/Export → Import Data → From File**.

### **2. Exemplos de Requisições**

#### **Criar Usuário (POST)**
```bash
POST http://localhost:8080/api/users
```
**Body (JSON):**
```json
{
  "fullName": "Maria Silva",
  "email": "maria@example.com",
  "phone": "+55 11 98765-4321",
  "birthDate": "1995-05-20",
  "userType": "ADMIN"
}
```

#### **Buscar Todos (GET)**
```bash
GET http://localhost:8080/api/users
```

---

## **📡 Endpoints**

| Método | Endpoint | Descrição |  
|--------|----------|------------|  
| POST | `/api/users` | Cria um novo usuário |  
| GET | `/api/users` | Lista todos os usuários |  
| GET | `/api/users/{id}` | Busca usuário por ID |  
| PUT | `/api/users/{id}` | Atualiza um usuário |  
| DELETE | `/api/users/{id}` | Remove um usuário |  

---

## **⚠️ Tratamento de Erros**

| Código | Descrição | Exemplo |  
|--------|-----------|---------|  
| `400 Bad Request` | Dados inválidos | `{ "email": "Deve ser um e-mail válido" }` |  
| `404 Not Found` | Usuário não existe | `{ "message": "User not found" }` |  
| `500 Internal Error` | Erro no servidor | `{ "error": "Database connection failed" }` |

