# Conference Service

### Running:

Windows: mvnw spring-boot:run

Linux/macOS: ./mvnw spring-boot:run

### Endpoints:

- GET /conference - get conference plan
- GET /reservations?login={login} - get user's lecture reservations
- POST /reservations?login={login}&email={email} - create lecture reservation for a user
- DELETE /reservations/{id} - delete reservation
- GET /users - get all users
- PUT /users - update user's email
- GET /stats?type={type} - get statistics (for lecture or path)
