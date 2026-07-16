## Overview

Notification Service is an event-driven backend application that demonstrates asynchronous message processing using RabbitMQ. It supports Email, SMS, and Push notifications with automatic retries, dead-letter queues, Dockerized deployment, REST APIs, and Continuous Integration using GitHub Actions.


# Notification Service

![Build](https://github.com/UtkarshPardhi/notification-service/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.16-brightgreen)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-4.x-orange)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)
![JUnit5](https://img.shields.io/badge/JUnit5-Tested-success)
![Mockito](https://img.shields.io/badge/Mockito-Unit%20Testing-blue)

A production-ready **Notification Service** built using **Java 21**, **Spring Boot 3.5**, and **RabbitMQ**.

The application demonstrates **asynchronous message processing** using RabbitMQ Topic Exchanges and supports **Email**, **SMS**, and **Push Notifications** with **Spring Retry**, **Dead Letter Queues (DLQ)**, **Docker**, **Swagger/OpenAPI**, and **GitHub Actions CI**.

---

# Features

- Asynchronous notification processing using RabbitMQ
- Email, SMS, and Push notification support
- Topic Exchange based message routing
- Spring Retry with automatic retry mechanism
- Dead Letter Queue (DLQ) support
- RESTful APIs using Spring Boot
- Request validation using Jakarta Validation
- Swagger / OpenAPI documentation
- Docker & Docker Compose support
- Unit Testing with JUnit 5 and Mockito
- GitHub Actions Continuous Integration (CI)

---

# Tech Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.5.16 |
| Spring AMQP | 3.2.x |
| RabbitMQ | 4.x |
| Maven | 3.x |
| Docker | Latest |
| Swagger OpenAPI | Latest |
| GitHub Actions | CI |
| JUnit 5 | Latest |
| Mockito | Latest |

---

# Architecture

```text
                    REST API
                        │
                        ▼
            Notification Controller
                        │
                        ▼
             Notification Producer
                        │
                        ▼
         RabbitMQ Topic Exchange
        ┌─────────┬─────────┬─────────┐
        ▼         ▼         ▼
   Email Queue  SMS Queue Push Queue
        │         │         │
        ▼         ▼         ▼
 Email Consumer SMS Consumer Push Consumer
        │         │         │
        ▼         ▼         ▼
 Email     SMS      Push Services

          Spring Retry (3 Attempts)
                    │
                    ▼
        Dead Letter Exchange (DLX)
                    │
                    ▼
         Dead Letter Queue (DLQ)
```

---

# Screenshots

## Swagger UI

### API Overview

Interactive API documentation generated using Springdoc OpenAPI.

![Swagger Overview](images/swagger-overview.png)

---

### Publish Notification Endpoint

Shows the request body, example payload and response documentation.

![Swagger Endpoint](images/swagger-endpoint.png)

---

## RabbitMQ Dashboard

### Queues

Displays the primary queues and dead-letter queues.

![RabbitMQ Queues](images/rabbitmq-queues.png)

---

### Exchanges

Displays the Topic Exchange and Dead Letter Exchange responsible for routing notifications.

![RabbitMQ Exchanges](images/rabbitmq-exchanges.png)

---

# Project Structure

```text
src
├── main
│   ├── java
│   │   ├── config
│   │   ├── constants
│   │   ├── consumer
│   │   ├── controller
│   │   ├── dto
│   │   ├── enums
│   │   ├── exception
│   │   ├── producer
│   │   ├── response
│   │   └── service
│   └── resources
└── test
    └── java
```

---

# Running the Application

## Clone Repository

```bash
git clone https://github.com/UtkarshPardhi/notification-service.git

cd notification-service
```

## Build

```bash
mvn clean package
```

## Run with Docker

```bash
docker compose up --build
```

Application URL

```
http://localhost:8080
```

---

# REST API

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/notifications` | Publish Email, SMS or Push Notification |

### Sample Request

```json
{
  "type": "EMAIL",
  "recipient": "user@example.com",
  "subject": "Welcome",
  "message": "Welcome to Notification Service"
}
```

---

# Swagger Documentation

```
http://localhost:8080/swagger-ui/index.html
```

---

# RabbitMQ Management

```
http://localhost:15672
```

Default Credentials

```
Username : guest
Password : guest
```

---

# Testing

Run all tests

```bash
mvn test
```

### Current Test Coverage

- Notification Producer
- Email Consumer
- SMS Consumer
- Push Consumer
- Notification Controller

---

# Continuous Integration

This project uses **GitHub Actions** for Continuous Integration.

Every push and pull request automatically:

- Builds the project
- Executes all unit tests
- Verifies project integrity

Workflow Location:

```text
.github/workflows/ci.yml
```

---

# Docker

### Build & Run

```bash
docker compose up --build
```

### Stop Containers

```bash
docker compose down
```

---

# Future Improvements

- SMTP Email Integration
- SMS Gateway Integration
- Firebase Cloud Messaging (FCM)
- Integration Testing using Testcontainers
- Kubernetes Deployment
- Prometheus & Grafana Monitoring
- Centralized Logging
- Metrics & Health Checks

---

# Author

**Utkarsh Pardhi**

MCA Graduate | Java Backend Developer

**Skills:** Java • Spring Boot • RabbitMQ • Docker • REST APIs • JUnit • Mockito • GitHub Actions

---

# License

This project is intended for educational and portfolio purposes.

Testing GitHub Actions