# Network Monitoring (learning project)

Starter Spring Boot app for learning how to monitor simulated and reachable network devices.

## Prerequisites

- Java 17 or newer
- Maven 3.6.3 or newer
- Docker with Docker Compose, for the local PostgreSQL database

## Run locally

From this directory, start PostgreSQL and then the application:

```sh   
docker compose up -d
mvn spring-boot:run
```

Check the starter API at `http://localhost:8080/api/v1/status` and Actuator health at `http://localhost:8080/actuator/health`.

Database connection values can be overridden with `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` environment variables. The defaults are for local learning only.

## Dependencies

- Spring Web: REST endpoints.
- Spring Data JPA: persistence and repository abstractions.
- Validation: validate incoming API requests.
- Actuator: health and application metrics endpoints.
- PostgreSQL driver: database connectivity.
- Flyway: versioned database schema migrations.
- Spring Boot Test and H2: test support and an in-memory database for future tests.

Nginx will be configured separately as a reverse proxy when we reach the deployment milestone. Authentication, Prometheus export, WebSockets, and a broker are intentionally deferred until the MVP needs them.
# Network_Management
