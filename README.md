# Bug Wars AI CRUD Service
This microservice stores all AI Script objects in a database.  In local environment, this microservice creates an H2 in-memory 
database.  When deployed to Heroku, the microservice connects to a PostGres database hosted in the cloud.

## `GET /ai`
Returns array of AI Script objects

### Response Body
```json
[
  {
    "id": "1",
    "name": "Mover",
    "script": "move move move"
  },
  {
    "id": "2",
    "name": "Shaker",
    "script": "turnRight turnLeft"
  },
  {
    "id": "3",
    "name": "Attacker",
    "script": "attack attack attack"
  }
]
```

## `POST /ai`
Attempts to save a new AI Script.
* Returns 400 if any fields are null
* Returns 409 if duplicate name
* Returns 200 if created.

### Request Body
```json
{
  "name": "Name of Script",
  "script": "move move move"
}
```
### Response Body
```json
{
  "id": "Id assigned if successful",
  "name": "Name of Script",
  "script": "move move move",
  "error": "Reported error message, default is null"
}
```

## Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

## Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

