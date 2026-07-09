# Notification Service

A production-ready Notification Service built with **Java 21**, **Spring Boot 3**, and **RabbitMQ**. This application processes Email, SMS, and Push notifications asynchronously using RabbitMQ Topic Exchanges, Spring Retry, and Dead Letter Queues (DLQ).

---

## Features

- Asynchronous notification processing
- Email, SMS, and Push notification support
- RabbitMQ Topic Exchange routing
- Spring Retry with automatic retries
- Dead Letter Queue (DLQ)
- REST APIs using Spring Boot
- Request validation
- Swagger/OpenAPI documentation
- Docker & Docker Compose support
- Unit Testing using JUnit 5 and Mockito

---

## Tech Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.5.16 |
| Spring AMQP | 3.2.x |
| RabbitMQ | 4.x |
| Maven | 3.x |
| Docker | Latest |
| JUnit 5 | Latest |
| Mockito | Latest |
| Swagger OpenAPI | Latest |

---

## Architecture

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

             Spring Retry
                    │
                    ▼
          Dead Letter Exchange
                    │
                    ▼
            Dead Letter Queue
```

---

## Project Structure

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
│   │   ├── producer
│   │   ├── response
│   │   └── service
│   └── resources
└── test
    └── java
```

---

## Running the Application

### Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/notification-service.git

cd notification-service
```

### Build

```bash
mvn clean package
```

### Run with Docker

```bash
docker compose up --build
```

---

## REST API

### Publish Notification

```
POST /api/notifications
```

Sample Request

```json
{
  "type": "EMAIL",
  "recipient": "user@example.com",
  "subject": "Welcome",
  "message": "Welcome to Notification Service"
}
```

---

## Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

## RabbitMQ Management

```
http://localhost:15672
```

Default Credentials

```
Username : guest
Password : guest
```

---

## Testing

Run all tests

```bash
mvn test
```

Implemented Tests

- Producer Unit Tests
- Consumer Unit Tests
- Controller Unit Tests

---

## Docker

Build and run

```bash
docker compose up --build
```

Stop containers

```bash
docker compose down
```

---

## Future Improvements

- SMTP Email Integration
- SMS Gateway Integration
- Firebase Push Notifications
- Integration Testing with Testcontainers
- GitHub Actions CI/CD
- Kubernetes Deployment
- Monitoring using Prometheus & Grafana

---

## Author

**Utkarsh Pardhi**

MCA Graduate | Java Backend Developer

Java • Spring Boot • RabbitMQ • Docker • REST APIs • JUnit • Mockito