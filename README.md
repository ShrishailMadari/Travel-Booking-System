# Travel-Booking-System
Travel Booking System – Architecture Overview



Microservices & Responsibilities
API Gateway

Route requests

Validate JWT tokens

Apply rate limiting (Bucket4j/Redis)

Redirect to specific services

Auth Service

Login/Signup

JWT + OAuth2 token generation

Token validation support

Travel Booking Service (Facade Layer + Saga Orchestrator)

Facade interface to clients

Orchestrates booking flow (Flight → Hotel → Car → Payment)

Triggers compensation (cancel calls) on failure

Publishes booking status events to Kafka

Flight Service

Books flights

Cancels flight reservations

Maintains flight schedule DB (PostgreSQL)

Hotel Service

Reserves hotels

Cancel reservations

Rate filtering, date search, etc.

Car Service

Manages car rentals

Cancel API for SAGA rollback

Car availability logic

Payment Service

Deducts amount after all bookings succeed

Cancels/refunds in case of rollback

Handles strategy pattern for multiple gateways (UPI, card, wallet)

Notification Service

Listens to Kafka events

Sends booking confirmation/cancellation notifications via email/SMS

Uses Observer pattern (event-based)

User Profile Service (Optional)

Stores user trip history, preferences, frequent flyer program

Uses MongoDB for flexible schema

Admin Dashboard Service (Optional)

View booking traffic, logs, monitoring, compensation reports

Pulls data from Kafka topics or a reporting DB



Infra Components
Eureka Server – For service registration/discovery

Spring Cloud Config Server – Centralized configuration

Zipkin + Sleuth – Distributed tracing

Kafka – For async communication and event-based Saga

Redis – For caching hotel/car availability

Prometheus + Grafana – Monitoring

ELK Stack – Logging

Docker + Kubernetes – Containerization and orchestration

Flyway – DB versioning and migration





