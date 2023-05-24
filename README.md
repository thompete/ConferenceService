# Conference Service

### Running:

Windows: mvnw spring-boot:run

Linux/macOS: ./mvnw spring-boot:run

### Endpoints:

- GET /conference - get conference plan
- GET /reservations/login - get user's lecture reservations
- POST /reservations - create lecture reservation
- DELETE /reservations/{id} - delete reservation
- GET /users - get all users
- PUT /users - update user's email !FIXME
- GET /stats?for={"lectures"|"paths"}&order={"asc"|"desc"} - get statistics
