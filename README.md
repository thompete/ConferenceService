# Conference Service

### Running:

Windows: mvnw spring-boot:run

Linux/macOS: ./mvnw spring-boot:run

### Endpoints:

- GET /conference - get conference plan
- GET /registrations?login={login} - get lectures user is registered for
- POST /registrations - register user for a lecture
- DELETE /registrations/{id} - deregister user from a lecture
- GET /users - get all users
- PATCH /users/{id} - update user's email
- GET /stats?type={type} - get statistics (for lecture or path)
