# Redis Toolkit

A Spring Boot application that integrates **Redis** and **PostgreSQL (JPA)** into a unified toolkit, providing a foundation for building Redis-backed data access patterns alongside relational persistence.

---

## Tech Stack

| Layer             | Technology                        |
|-------------------|-----------------------------------|
| Language          | Java 25                           |
| Framework         | Spring Boot 4.0.5                 |
| Cache / Data Store| Redis (Spring Data Redis)         |
| Relational DB     | PostgreSQL (Spring Data JPA / Hibernate) |
| API Docs          | SpringDoc OpenAPI 3 (Swagger UI)  |
| Build Tool        | Gradle Wrapper                    |
| Test Coverage     | JaCoCo                            |

---

## Prerequisites

- **Java 25** JDK installed and on `PATH`
- **Redis** running on `localhost:6379` (default)
- **PostgreSQL** running on `localhost:5432` with a database named `javatest`
- Environment variable `DB_PASSWORD` set to your PostgreSQL password

---

## Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd redistoolkit
```

### 2. Set the database password environment variable

```powershell
$env:DB_PASSWORD = "your_postgres_password"
```

### 3. Run the application (Windows)

```powershell
.\gradlew.bat bootRun
```

### 4. Run the application (Linux / macOS)

```bash
./gradlew bootRun
```

The application will start on **http://localhost:8080** by default.

---

## API Documentation

Once the application is running, the interactive Swagger UI is available at:

```
http://localhost:8080/swagger-ui/index.html
```

The OpenAPI JSON spec is available at:

```
http://localhost:8080/v3/api-docs
```

---

## Project Structure

```
src/
└── main/
│   ├── java/com/cleancoders/redistoolkit/
│   │   ├── RedistoolkitApplication.java   # Spring Boot entry point
│   │   ├── config/
│   │   │   └── JpaConfig.java             # JPA / Hibernate / DataSource config
│   │   ├── entity/                        # JPA entity classes
│   │   └── repository/                   # Spring Data JPA repositories
│   └── resources/
│       └── application.yml               # App configuration
└── test/
    ├── java/com/cleancoders/redistoolkit/ # Unit & integration tests
    └── resources/
        └── application.yml               # Test configuration (H2 in-memory DB)
```

---

## Configuration

Key settings in `src/main/resources/application.yml`:

| Property                         | Default / Value                          |
|----------------------------------|------------------------------------------|
| `spring.datasource.url`          | `jdbc:postgresql://localhost:5432/javatest` |
| `spring.datasource.username`     | `postgres`                               |
| `spring.datasource.password`     | `${DB_PASSWORD}` (environment variable)  |
| `spring.jpa.hibernate.ddl-auto`  | `update`                                 |

Redis connection defaults to `localhost:6379` (Spring Boot auto-configuration).

---

## Building & Testing

### Run all tests

```powershell
.\gradlew.bat test
```

### Generate code coverage report

```powershell
.\gradlew.bat jacocoTestReport
```

The HTML report is generated at:

```
build/reports/jacoco/test/html/index.html
```

### Build the project (includes tests + coverage)

```powershell
.\gradlew.bat build
```

---

## Dependencies

All dependency versions are centrally managed via the [Gradle Version Catalog](gradle/libs.versions.toml) and the Spring Boot BOM.

| Dependency                          | Version            |
|-------------------------------------|--------------------|
| Spring Boot                         | 4.0.5              |
| Spring Boot Starter Web             | (BOM managed)      |
| Spring Boot Starter Data JPA        | (BOM managed)      |
| Spring Boot Starter Data Redis      | (BOM managed)      |
| springdoc-openapi-starter-webmvc-ui | 3.0.0              |
| PostgreSQL JDBC Driver              | 42.6.0             |
| H2 (test scope)                     | (BOM managed)      |

---

## References

- [Spring Data Redis Documentation](https://docs.spring.io/spring-boot/4.0.5/reference/data/nosql.html#data.nosql.redis)
- [Spring Boot Gradle Plugin](https://docs.spring.io/spring-boot/4.0.5/gradle-plugin)
- [Gradle Build Scans](https://scans.gradle.com#gradle)
- [Messaging with Redis Guide](https://spring.io/guides/gs/messaging-redis/)

