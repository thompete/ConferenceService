# Conference Service

A REST API that allows users to book places for conference lectures made in Spring Boot framework.

### Running:

In project's directory:

    ./mvnw spring-boot:run

### Endpoints:

- GET /api/conference - get conference info and plan
- POST /api/reservations - create lecture reservation (registering user if necessary)

        {
            "login": "{login}",
            "email": "{email}",
            "lectureId": {lectureId}
        }

- GET /api/reservations/{login} - get user's reservations
- DELETE /api/reservations/{id} - delete reservation
- GET /api/users - get all users
- PUT /api/users - update user's email

        {
            "login": "{login}",
            "email": "{email}"
        }

- GET /api/stats?for={"lectures"|"paths"}&order={"asc"|"desc"} - get statistics

Postman collection for testing:

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/2730052-38760c4c-13fd-46d7-ab3d-41f3eaba7e34?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D2730052-38760c4c-13fd-46d7-ab3d-41f3eaba7e34%26entityType%3Dcollection%26workspaceId%3D7cc53cfd-41d7-4e0c-83e4-fbe884b9236e)