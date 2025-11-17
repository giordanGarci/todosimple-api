-----

# 📌 CRUD API with Spring Boot and JWT Authentication

This project is a **REST API** built with **Spring Boot**, providing full **CRUD** (Create, Read, Update, Delete) operations and **JWT-based authentication**.

Its goal is to offer a clean, modular, and scalable structure for applications that require secure data handling and access control.

-----

## 🔧 Technologies Used

The project leverages a modern and robust tech stack:

  * **Java 17+**
  * **Spring Boot** (Web, Validation, JPA, Security)
  * **JWT** (custom `JwtUtil` implementation)
  * **Hibernate / JPA**
  * **Database:** H2 / MySQL (configurable)
  * **Maven**

-----

## 🔐 Authentication & Authorization

Authentication is handled using **JSON Web Tokens (JWT)**, ensuring a secure and stateless mechanism:

  * **Token generation** on successful login.
  * **Token validation** for all protected routes.
  * **Custom security filters** for seamless integration.
  * **Role-based access control** can be implemented and extended when needed.

-----

## 📚 Features

| Feature | Description |
| :--- | :--- |
| **CRUD Operations** | Create, read, update, and delete entities through dedicated endpoints. |
| **Authentication** | Secure login and JWT token generation. |
| **Endpoint Security** | Clear separation between **Public** and **Private (Protected)** endpoints. |
| **Exception Handling** | Robust **Global exception handling** for clean and informative error responses. |
| **Clean Architecture** | Organized structure with dedicated layers: `Services`, `Controllers`, `DTOs`, and `Repositories`. |

-----

## ▶️ How to Run

Follow these simple steps to get the API running locally:

1.  **Clone the repository:**
    ```bash
    git clone [repository-url]
    cd [repository-name]
    ```
2.  **Configure the database:**
      * Edit the database connection properties in the `src/main/resources/application.properties` file (default uses H2).
3.  **Run the application:**
      * Using Maven:
        ```bash
        mvn spring-boot:run
        ```
      * Or, run the main class from your IDE (e.g., IntelliJ, VS Code).
4.  **Access the API:**
      * The API will be running on: **`http://localhost:8080`**

-----

