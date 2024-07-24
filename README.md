# cdr-app


The cdr-app is a simple application that allows maintenance of Charge Detail Records.

It is a Spring Boot application that uses Hibernate JPA to store messages in a PostgreSQL database, aided by Flyway

POST /cdr

GET /cdr/{id}

GET /cdr/bycar/{carId}

A Makefile allows easy dockerization:

jar:
    mvn package -DskipTests

build:
    docker compose build

up:
    docker compose up

Flyway will create the necessary tables in the database

Springdoc: http://localhost:8080/v3/api-docs

Wagger: http://localhost:8080/swagger-ui/index.html

The Project
As an e-mobility charging solutions platform, we would like to provide a REST API that is capable of managing Charge Detail Records (CDR) in real time to a network of Charge Point Operators (CPO).

In order to achieve that goal, a CDR contract and a set of endpoints are required as follows:

Charge Detail Record fields (no pre defined field types are imposed)
Session identification
Vehicle identification
Start time
End time
Total cost

Endpoints

Create a Charge Detail Record
The "End time" cannot be smaller than "Start time"
The "Start time" of an upcoming Charge Detail Record for a particular vehicle must always be bigger than the "End time" of any previous Charge Detail Records.
The "Total cost" must be greater than 0

Get a Charge Detail Record by id

Search all Charge Detail Records for a particular vehicle

"Start time" and "End time" fields must be sortable

Constraints
Use Java 1.8+
Use the Spring Framework
Use Maven or Gradle as a build tool
Some additional guidelines
Does your project have instructions on how to build and run it?
Does your project consider any form of testing and coverage?
Does your project implement any design pattern(s) to help you solve known issues?
Try to make your project as production ready as possible
Transparency is key, so if there are any known bugs/issues it could be a good idea to document them
