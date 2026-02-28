
# 🌐 Spring Cloud Microservices

A **Spring Cloud microservices project** with **Eureka Server**, **Order Service**, and **API Gateway**, demonstrating service discovery, routing, and modular service design using **Spring Boot** and **Spring Cloud**.

---

## 📁 Project Structure
<img width="894" height="848" alt="Screenshot 2026-03-01 at 12 50 45 AM" src="https://github.com/user-attachments/assets/fd1562d0-dde7-484e-9b0f-9f264100c278" />


---

## ⚡ Features

- **Eureka Server** – Service registry for discovery of microservices  
- **Order Service** – REST API to manage orders  
- **API Gateway** – Routes requests and load-balances microservices  
- **Spring Boot** – Lightweight, modular microservice framework  
- **Extensible** – Add new services easily

---

## 🚀 Getting Started

### Prerequisites

- Java 21+  
- Maven  
- Spring Boot-compatible IDE (IntelliJ, Eclipse)  
- Docker (optional)

### Run Services Locally

```bash
# Eureka Server
cd eureka-server
mvn spring-boot:run

# Order Service
cd order-service
mvn spring-boot:run

# API Gateway
cd api-gateway
mvn spring-boot:run

# End points
http://localhost:8761/
ttp://localhost:8080/orders
