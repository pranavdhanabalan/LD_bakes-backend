# 🎂 LD Bakes - Bakery Management Backend

[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/technologies/downloads/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Database](https://img.shields.io/badge/Database-MySQL-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**LD Bakes Backend** is a robust, production-ready RESTful API built on Spring Boot for managing a bakery's menu and handling secure administrative access. The application leverages Spring Security with stateless JSON Web Token (JWT) authentication, Hibernate ORM for database mapping, and MySQL for persistence.

---

## 🚀 Core Features

- 🔒 **Stateless Security**: Robust JWT-based authentication featuring access tokens (15-minute expiry) and refresh tokens (7-day expiry).
- 🍰 **Menu Catalog Management**: Complete CRUD operations for bakery items (name, price, image URL) with strict validation.
- 🚦 **Fine-grained Access Control**: Public read access to the menu, with create/delete administrative actions locked behind JWT validation.
- ⚙️ **Database Synchronization**: Hibernate-managed schema updates (`ddl-auto=update`) for seamless database version alignment.
- 🛡️ **Data Validation**: Strict server-side verification using `jakarta.validation` to prevent corrupt data entry.

---

## 🛠️ Technology Stack

- **Framework**: Spring Boot 4.1.0 (Web MVC, Data JPA, Security, Validation)
- **Language**: Java 21 (LTS)
- **Database**: MySQL Server
- **Authentication**: JWT (JSON Web Token) via `io.jsonwebtoken` (jjwt 0.12.6)
- **Build System**: Apache Maven 3.x
- **Boilerplate Reduction**: Project Lombok

---

## 📂 Project Structure

The project follows a standard layered architecture:

```text
ldbakes
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/foods/ldbakes
│   │   │       ├── Config         # Security & Password Encoder configurations
│   │   │       ├── Controller     # REST Controllers for endpoints
│   │   │       ├── DTO            # Request & Response Data Transfer Objects
│   │   │       ├── Model          # JPA Entity definitions (User, Menu)
│   │   │       ├── Repository     # Spring Data JPA repositories
│   │   │       ├── Security       # JWT filters, helpers, and UserDetailsService
│   │   │       └── Service        # Business logic handlers (Auth, Menu)
│   │   └── resources
│   │       ├── static
│   │       ├── templates
│   │       └── application.properties # Main configuration file
└── pom.xml
```

---

## ⚙️ Environment Configuration

The backend is configured to read database connections and security keys via environment variables for security and compatibility.

| Variable Name | Description | Example Value |
| :--- | :--- | :--- |
| `DATABASE_URL` | JDBC Connection URL to your MySQL database | `jdbc:mysql://localhost:3306/ldbakes?useSSL=false&allowPublicKeyRetrieval=true` |
| `DB_USERNAME` | MySQL database user | `root` |
| `DB_PASSWORD` | MySQL database password | `password123` |
| `JWT_SECRET` | Secret key used for signing JWTs (must be at least 256-bit Hex/String) | `5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437` |

---

## 🏃 Getting Started Locally

### 1. Prerequisites
- **Java JDK 21** installed and configured on your `PATH`.
- **MySQL Server** running.
- **Maven** (or use the included Maven wrapper `./mvnw`).

### 2. Database Setup
Create a new MySQL database named `ldbakes`:
```sql
CREATE DATABASE ldbakes;
```

### 3. Environment Setup
Export the required environment variables in your terminal:
```bash
export DATABASE_URL="jdbc:mysql://localhost:3306/ldbakes"
export DB_USERNAME="your_mysql_username"
export DB_PASSWORD="your_mysql_password"
export JWT_SECRET="your_secure_256bit_jwt_secret_here"
```

### 4. Build and Run
Clean compile the project and start the application:
```bash
# Compile and package
./mvnw clean install

# Run the Spring Boot app
./mvnw spring-boot:run
```
The server will start up by default on port `8080`.

---

## 📡 API Reference

### 🔐 Authentication

#### Admin Login
Authenticates an admin user and returns access and refresh tokens.
* **Endpoint**: `POST /api/v1/auth/login`
* **Access**: Public
* **Request Headers**: `Content-Type: application/json`
* **Request Body**:
  ```json
  {
    "email": "admin@ldbakes.com",
    "password": "yourpassword"
  }
  ```
* **Success Response (200 OK)**:
  ```json
  {
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbi...",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbi...",
    "expiresIn": 900
  }
  ```

---

### 🍰 Menu Management

#### Get All Menu Items
Retrieves a complete list of all active bakery products on the menu.
* **Endpoint**: `GET /menu/get`
* **Access**: Public
* **Success Response (200 OK)**:
  ```json
  [
    {
      "itemId": 1,
      "dishName": "Chocolate Truffle Cake",
      "dishPrice": 25.99,
      "imageUrl": "https://example.com/images/truffle.jpg"
    },
    {
      "itemId": 2,
      "dishName": "Red Velvet Pastry",
      "dishPrice": 4.50,
      "imageUrl": "https://example.com/images/redvelvet.jpg"
    }
  ]
  ```

#### Add Menu Item
Creates a new bakery item.
* **Endpoint**: `POST /menu/add`
* **Access**: Protected (Requires valid JWT Access Token)
* **Request Headers**:
  - `Content-Type: application/json`
  - `Authorization: Bearer <accessToken>`
* **Request Body**:
  ```json
  {
    "dishName": "Blueberry Cheesecake",
    "dishPrice": 28.50,
    "imageUrl": "https://example.com/images/blueberry.jpg"
  }
  ```
* **Success Response (200 OK)**:
  ```json
  {
    "itemId": 3,
    "dishName": "Blueberry Cheesecake",
    "dishPrice": 28.50,
    "imageUrl": "https://example.com/images/blueberry.jpg"
  }
  ```

#### Delete Menu Item
Deletes an item from the menu catalog by ID.
* **Endpoint**: `DELETE /menu/delete/{itemId}`
* **Access**: Protected (Requires valid JWT Access Token)
* **Request Headers**:
  - `Authorization: Bearer <accessToken>`
* **Path Variables**:
  - `itemId` (Long) - The ID of the item to delete.
* **Success Response (200 OK)**:
  ```json
  true
  ```

---

## 🔒 Security & Token Verification

Except for `/menu/get` and `/api/v1/auth/login`, all endpoints are secured.
To make requests to protected routes, supply the `accessToken` in the request header:

```http
Authorization: Bearer <your_jwt_access_token>
```

Tokens are signed using `HMAC-SHA` algorithm using the secret key provided in `JWT_SECRET`.
- **Access Token Expiry**: 15 minutes (`900000ms`)
- **Refresh Token Expiry**: 7 days (`604800000ms`)
