# Task Management REST API

A RESTful Task Management system built with Spring Boot.

The application runs on:

http://localhost:8081

---

## Server Configuration

- Server Port: 8081
- Base URL: http://localhost:8081/api/v1/tasks

---

## Features

- Create a task
- Get task by ID
- Get all tasks with pagination
- Search tasks with pagination
- Update task
- Update task status (done / not done)
- Delete task

---

## Database

This project uses **H2 Database (In-Memory)**.

H2 is a lightweight embedded database mainly used for development and testing.


Note:
The database resets every time the application restarts (because it runs in-memory mode).

---

## Technology Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven

---

## Architecture

- Controller Layer → REST endpoints
- Service Layer → Business logic
- Repository Layer → Data persistence
- DTO Layer → Request and Response models

---

## Running the Project

### 1. Build the project

mvn clean install

### 2. Run the application

mvn spring-boot:run

### 3. Open in browser

http://localhost:8081

---

## HTTP Status Codes Used

- 200 → Success
- 204 → No Content (after delete)
- 404 → Resource Not Found

---
## API doc

- the documentation is at the url : http://localhost:8081/swagger-ui/index.html
- Json Documentation is at  : http://localhost:8081/v3/api-docs

---

## Notes

- Pagination is supported using: page, size, sort
- Designed for educational and backend practice purposes