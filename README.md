# Inclusify - Backend Getting Started

Welcome to the Inclusify project's backend services! This section provides an overview of the backend services, how they work together, and how to set them up.

Technologies Used
Spring Boot for Microservices
Keycloak for Security
Eureka for Service Discovery

Backend Services
Event Service: Service for managing events and meetups.
Feed Service: Service for social media feeds and posts.
Job Service: Service for job postings and applications.
Marketplace Service: Service for buying and selling goods and services.
Skill Service: Service for managing and showcasing skills.
User Service: Service for user management and authentication.

#  Getting Started
To get started, follow these general steps:

Clone this repository.
```python
git clone https://github.com/youssefalmia/inclusify.git
```

For each microservice:
Navigate to the microservice's directory.
Build and run the service using Maven 
```python
mvn spring-boot:run
```
  
Set up Keycloak for user authentication and authorization.

Use Eureka for service registration and discovery.

#  Postman Testing
API Endpoints
Here are some of the main API endpoints for each service:

Event Service: /events
Feed Service: /feeds
Job Service: /jobs
Marketplace Service: /marketplace
Skill Service: /skills
User Service: /users
For detailed API documentation, you can explore each service's Swagger UI.

# Additional Resources
<a href="https://github.com/FouratBenDhafer99/Inclusify-MS-frontoffice/tree/main">Link</a> to Frontend Repository
