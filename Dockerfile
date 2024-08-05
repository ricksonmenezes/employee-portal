#!/bin/bash
FROM maven:3.9.6 AS maven

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN mvn package -DskipTests -e

FROM openjdk:17-jdk-slim

ARG JAR_FILE=employeeportal-0.0.1-SNAPSHOT.jar
#ADD target/assignment-0.0.1-SNAPSHOT.jar assignment.jar
WORKDIR /opt/app

COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

EXPOSE 8081

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n","-Dspring.profiles.active=dev","-jar", "employeeportal-0.0.1-SNAPSHOT.jar"]
