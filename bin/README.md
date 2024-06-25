# EduVerse E-Learning System

## Pre-requisites
Before running the project, ensure you have the following installed:

- Java Development Kit (JDK)
	- JDK 17
- Integrated Development Environment (IDE)
	- Eclipse or IntelliJ IDEA or VS Code
- Build Tool
	- Apache Maven (Version 3.x)
- Database
	- MySQL (Version 8)
- Version Control System
	- Git (Latest stable version)

## How to Run
1. Clone the repository:

```
git clone https://github.com/sannlynnhtun-coding/EduVerse
```

2. Navigate to the project directory.

```
cd EduVerse/eduverse
```

3. Build the project using Maven.

```
mvn clean install
```

4. Run the application.

```
mvn spring-boot:run
OR
mvn spring-boot:run -Dspring.profiles.active=dev
```
	
5. Access the application through a web browser at.

```
http://localhost:8080
```



## Swagger with Spring OpenAPI
This project utilizes Swagger to provide interactive API documentation. Follow these steps to access the API documentation:

1. After running the application, navigate to `http://localhost:8080/swagger-ui.html` in your web browser.
2. This will open the Swagger UI, where you can explore the available API end points, parameters, request bodies, and responses.
3. Use the Swagger UI interface to interact with the API, including testing end points directly from the browser.


## Actuator End-points
Spring Boot Actuator provides several end points to monitor and interact with the application. Here are some useful endpoints:

- `/actuator/health` : Provides basic health information about the application.

You can access these end points by appending them to your application's base URL (e.g., `http://localhost:8080/actuator/health`).



## Coding Standards
### Java Class Format
- Use CamelCase for class names (e.g., `UserService`, `CourseController`).
- Class names should be nouns and should represent a single responsibility.
- Class files should be named after the class they contain, with the file extension `.java` (e.g., `UserService.java`).

### Method Format
- Use camelCase for method names (e.g., `getUsers()`, `calculateProgress()`).
- Method names should be verbs or verb phrases, indicating actions performed by the method.
- Methods should have clear and concise purposes, adhering to the Single Responsibility Principle.
- Methods should be properly documented using Java-doc comments to explain their purpose, parameters, and return values.

### General Coding Standards
- Follow the Java coding conventions outlined in the Oracle Java SE Coding Conventions.
- Use meaningful variable names that reflect their purpose.
- Limit the length of lines to 80-120 characters to ensure readability.
- Write modular and reusable code to promote maintainability.
- Utilize appropriate design patterns and best practices where applicable.

