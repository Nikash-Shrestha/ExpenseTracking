# Expense Tracking Backend

A backend REST API for an expense tracking application built with Java, Spring Boot, and PostgreSQL. The project provides user authentication, income and expense management, savings summaries, monthly financial reports, budget tracking, budget alerts, and dashboard summaries.

## Features

### Authentication and Authorization

- User registration and login
- JWT-based authentication
- Password encryption using BCrypt
- Protected endpoints for authenticated users
- User-specific data access

### Income Management

- Create, retrieve, update, and delete income records
- Track income sources, amounts, descriptions, and dates
- Retrieve income records belonging to the authenticated user

### Expense Management

- Create, retrieve, update, and delete expense records
- Categorize expenses
- Track expense amounts, descriptions, and dates
- Retrieve expenses belonging to the authenticated user

### Savings and Reports

- Calculate total income and expenses
- Calculate current savings
- Generate monthly financial reports
- View monthly income and expense totals
- View net savings and savings rate
- View expense breakdown by category

### Budget Management

- Create, retrieve, update, and delete budgets
- Set budget amounts, categories, and date ranges
- Track spending against budgets
- Calculate remaining budget and percentage used
- Generate budget alerts when spending reaches 80% or exceeds 100% of the budget

### Dashboard Summary

- View total income and expenses
- View current savings
- View total budgets
- View the number of exceeded budgets

### Additional Capabilities

- PostgreSQL database integration
- DTO-based request and response handling
- Input validation
- Centralized exception handling
- RESTful API design
- API documentation through Swagger UI

## Technology Stack

| Technology | Purpose |
|---|---|
| Java | Backend programming language |
| Spring Boot | Application framework |
| Spring Web | REST API development |
| Spring Security | Authentication and authorization |
| Spring Data JPA | Database access |
| Hibernate | ORM |
| PostgreSQL | Relational database |
| JWT | Token-based authentication |
| BCrypt | Password hashing |
| Maven | Dependency management and build |
| Lombok | Boilerplate reduction |
| MapStruct | DTO and entity mapping |
| SpringDoc OpenAPI | API documentation and Swagger UI |

## Prerequisites

Install the following before running the application:

- JDK 26 or the Java version declared in `pom.xml`
- Maven, or use the included Maven Wrapper if available
- PostgreSQL
- Git

## Getting Started

### 1. Clone the repository

```bash
git clone <your-repository-url>
cd Expanse_Tracking
```

### 2. Create the PostgreSQL database

Open PostgreSQL and execute:

```sql
CREATE DATABASE expense_tracking;
```

### 3. Configure application properties

Create or update `src/main/resources/application.properties` with your local configuration.

Example:

```properties
spring.application.name=Expanse_Tracking
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/expense_tracking
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

Configure any additional properties required by your application, including the JWT signing secret.

Do not commit database credentials, JWT secrets, or other sensitive configuration to GitHub. Use environment variables or a local configuration file excluded from version control.

### 4. Run the application

1. Open the project in IntelliJ IDEA.
2. Configure your database credentials and required environment variables.
3. Make sure PostgreSQL is running and the `expense_tracking` database exists.
4. Open `ExpanseTrackingApplication.java`.
5. Click the green **Run (▶)** button.
6. Check the Run console to confirm that the application starts successfully.

The application runs on port `8080` by default, unless configured otherwise.

## API Documentation

After starting the application, open Swagger UI:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Swagger UI allows you to explore and execute API requests directly from your browser.

For protected endpoints:

1. Register a user and log in.
2. Copy the JWT returned by the login endpoint.
3. Click **Authorize** in Swagger UI.
4. Paste the token into the Bearer authentication field.
5. Execute protected requests.

## Main API Endpoints

| Feature | Endpoint |
|---|---|
| Register | `POST /api/users/register` |
| Login | `POST /api/users/login` |
| Income CRUD | `/api/incomes` |
| Expense CRUD | `/api/expenses` |
| Savings summary | `GET /api/savings/summary` |
| Monthly report | `GET /api/reports/monthly?month={month}&year={year}` |
| Budget CRUD | `/api/budgets` |
| Budget status | `GET /api/budgets/{budgetId}/status` |
| Budget alert | `GET /api/budgets/{budgetId}/alert` |
| Dashboard summary | `GET /api/dashboard/summary` |

Protected endpoints require a valid JWT:

```http
Authorization: Bearer <your-jwt-token>
```

## Project Structure

The application follows a layered Spring Boot architecture. The main source code is organized into packages for responsibilities such as:

- `Config` — application and security configuration
- `controller` — REST API endpoints
- `dao` — business logic and data operations
- `Dto.Request` — request DTOs
- `Dto.Response` — response DTOs
- `Entity` — JPA entities
- `Exception` — custom exceptions and exception handling
- `Mapper` — DTO and entity mapping
- `Repository` — database access
- `Service` — supporting services

Refer to the source code for the exact package structure.

## Security Considerations

- Passwords are stored as BCrypt hashes, not plain text.
- Keep database credentials and JWT signing secrets outside version control.
- Use HTTPS in production.
- Ensure all protected operations are scoped to the authenticated user.
- Validate input on all API endpoints.
- Use a strong, private JWT signing secret.
- Configure production database credentials through environment-specific secrets.

## Future Improvements

Potential areas for further development include:

- Automated unit and integration tests
- Docker-based local development
- Continuous integration with GitHub Actions
- Cloud deployment
- Production monitoring and structured logging
- Recurring income and expense support
- Exporting financial reports to CSV or PDF

## License

No license has been specified for this project yet.
