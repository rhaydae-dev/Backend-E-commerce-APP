# 🛒 Backend E-commerce API

A **RESTful backend API** for an e-commerce application developed with **Java** and **Spring Boot**.

The application provides **user authentication**, **product management**, **order management**, and **role-based access control**. It uses **MySQL** for data persistence and **JWT** for authentication.

---

## ✨ Features

* 👤 **User registration and authentication**
* 🔐 **JWT-based authentication**
* 🛡️ **Role-based access control** with `USER` and `ADMIN` roles
* 📦 **Product management**

  * Create products
  * Retrieve products
  * Update products
  * Delete products
* 🔎 **Product search and pagination**
* 🛍️ **Order creation and management**
* 🗄️ **MySQL database integration**
* 📚 **API documentation** with Swagger / OpenAPI
* 🐳 **Docker-based MySQL database**

---

## 🛠️ Technologies

| Technology          | Version |
| ------------------- | ------- |
| **Java**            | 21      |
| **Spring Boot**     | 3.5.6   |
| **Spring Security** | —       |
| **JWT**             | —       |
| **Spring Data JPA** | —       |
| **Hibernate**       | —       |
| **MySQL**           | 8       |
| **Maven**           | —       |
| **Docker**          | —       |

---

## 🔐 Authentication

The application uses **JWT (JSON Web Token)** for authentication and **Spring Security** for securing the API.

After successful login, the user receives a JWT token that can be used to access protected endpoints.

```http
Authorization: Bearer <JWT_TOKEN>
```

---

## 📚 API Documentation

The API is documented using **Swagger / OpenAPI**.

When the application is running, Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger can be used to explore and test the available API endpoints.

---

## 🗄️ Database

The application uses **MySQL 8** for data persistence.

The MySQL database is configured to run using **Docker**, making the development environment easier to set up and reproduce.

---

## 🚀 Project Status

This is a **personal backend project** developed to practice and demonstrate:

* **Java backend development**
* **Spring Boot**
* **REST API development**
* **Spring Security and JWT authentication**
* **Database management with MySQL**
* **Docker**
* **API documentation with Swagger / OpenAPI**

The project is currently under development and may be extended with additional features in the future.
