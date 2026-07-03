# 💰 Where's My Money

Where's My Money is a secure RESTful Expense Tracker application built with Java, Spring Boot, Spring Security, JWT Authentication, Spring Data JPA, MySQL, Docker, and Docker Compose. It helps users manage their personal finances by tracking income, expenses, and budgets securely.

## 🚀 Features

- User Registration & Login
- JWT-based Authentication & Authorization
- Income Management
- Expense Tracking
- Budget Management
- MySQL Database Integration
- Dockerized Application
- Docker Compose for Multi-Container Deployment

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Docker
- Docker Compose

## 📁 Project Structure

```
src/
├── controller
├── service
├── repository
├── model
├── dto
├── config
├── filter
└── security
```

## ▶️ Run with Docker

### Build the application

```bash
./mvnw clean package -DskipTests
```

### Start containers

```bash
docker compose up --build
```

Application:
```
http://localhost:8080
```

## 🗄️ Database

- MySQL
- Managed using Docker Compose
- Persistent storage using Docker Volumes

## 🔐 Authentication

The application uses JWT (JSON Web Tokens) for secure authentication and authorization.

## 📌 Future Enhancements

- Expense Categories
- Budget Setting for Each Category
- Dashboard
- Category-wise Analysis

## 👨‍💻 Author

**Gurpal Singh**
