# Funding Opportunities API 

## ℹ About

### Purpose

This API provides a backend service that allows organisations to store and manage information about available funding for small business owners. The stored information includes the provider, funding limits, industry focus, and status of the funding (e.g., whether applications are currently being accepted). Users can filter and retrieve opportunities based on specific criteria, while the system ensures records are kept up-to-date with automatic timestamps.

I chose to build this project after reading Grant Thornton research which outlined that 70% of small businesses expect that they’ll need to apply for additional funding in 2025 because they are struggling to secure the resources required to support their growth. This motivated me to create a system that streamlines the tracking and management of funding opportunities, helping organisations more efficiently identify, monitor, and apply for the funding they need to grow.

### Tech Stack

- Java 21
- Spring Boot 3.5.5
- MySQL (via mysql-connector-j)
- JUnit Jupiter (unit testing)
- Mockito (mocking for tests)
- Maven (build and dependency management)

### Funding Opportunity Entity

Each funding opportunity includes:

- Unique ID (UUID)
- Provider name
- Industry focus (enum e.g. HEALTHCARE)
- Minimum funding amount 
- Maximum funding amount
- Status (enum e.g. PENDING)
- Last updated timestamp

### API Endpoints

This Spring Boot API manages funding opportunities for various providers and industries. The main features include:

| Endpoint                       | Method | Description                            |
| ------------------------------ | ------ | -------------------------------------- |
| `/api/funding`                 | GET    | Retrieve all funding opportunities     |
| `/api/funding/{id}`            | GET    | Retrieve a funding opportunity by ID   |
| `/api/funding`                 | POST   | Create a new funding opportunity       |
| `/api/funding/{id}`            | PUT    | Update an existing funding opportunity |
| `/api/funding/{id}`            | DELETE | Delete a funding opportunity           |
| `/api/funding/status/{status}` | GET    | Filter opportunities by funding status |

This project uses the Spring Boot default port so an example `GET` call would be http://localhost:8080/api/funding

Example queries to retrieve funding opportunities:
```
getAllFundingOpportunities();
getByFundingStatus(FundingStatus.PENDING);
```

## ⚒️ Using the API 

### Prerequisites

- Java 21
- Maven
- MySQL

Once the repo has been cloned, follow the following steps:

### Local Setup

Create a `local.properties` file in the location `java-api-assessment/src/main/resources` following the template below. Update the file with the information for your local SQL database. Replace `your_db_username` with your username and `your_db_password` with your password. 

```
spring.datasource.url=jdbc:mysql://localhost:3306/funding_db

# Replace "root" with your database user, if applicable
spring.datasource.username=your_db_username

# Specify your database user's password, if applicable. If your database user doesn't have a password set, delete the line below
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true
```

### Database Setup

1. Create the database

Make sure you are logged into your MySQL database. Create the database `funding_db` if it doesn't exist using the following command:

```sql
CREATE DATABASE funding_db;
```

It is essential that the database goes by this exact name because the application is configured to connect to it specifically.

2. Schema creation 

By default, Spring Boot with JPA/Hibernate will automatically create the `funding_opportunities` table with the appropriate columns and data types based on the entity classes when the application starts.

Optional: If you want to manage the schema manually, you can use the provided `schema.sql` file with the following command within sql:

```
SOURCE /path/to/schema.sql;
```

3. Insert sample data (optional)

If required running the file `data.sql` within sql will populate example records by using this command: 

```sql
SOURCE path/to/data.sql;
```

The application can now query funding opportunities immediately.

4. Verify the setup

After setup, you can confirm the table is set up directly and has data (if populated per above) by running these SQL queries:

```
USE funding_db;
DESCRIBE funding_opportunities;
SELECT * FROM funding_opportunities;
```

### Run the application

Use the following command to start the application:

```./mvnw spring-boot:run```

## 🧪 Testing

In order to run the unit tests, use the `mvn test` command. This will execute all tests in the project which currently only include the service level. 

## 📚 Some learnings

Common Issues with Database Setup

- Empty tables after running data.sql: This happened because the industry_focus and funding_status enums in MySQL didn’t match the values in the Java enum. Ensuring both sides use the same exact values (e.g., TECH, HEALTHCARE, etc.) fixed the issue.

- Table not appearing on first run: By default, Hibernate generates tables based on the entities. If the schema didn’t match expectations, using a schema.sql file or adjusting the spring.jpa.hibernate.ddl-auto property resolved it.

- BINARY(16) ID confusion: Initially, inserting test data was tricky because BINARY(16) expects raw binary values, not plain strings. This meant I had to use CAST('1111111111111111' AS BINARY(16)) when seeding the database. To simplify testing, I adjusted my sample IDs to a format works cleanly the schema.
