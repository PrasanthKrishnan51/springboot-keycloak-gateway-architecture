
# 🌐 Spring Cloud Microservices

A **Spring Cloud microservices project** with **Eureka Server**, **Order Service**, and **API Gateway**, demonstrating service discovery, routing, and modular service design using **Spring Boot** and **Spring Cloud**.

---

## 📁 Project Structure

<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/3178a454-383d-4848-a299-8ecded42e262" />


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
