#!/bin/bash

IMAGE_NAME="maven:3.8.1-openjdk-17-slim"

# Clean and verify with Maven inside a Docker container
docker run --rm -v "$PWD":/app -w /app "$IMAGE_NAME" mvn clean verify

# Build the Docker image for the Spring Boot application
docker run --rm -v "$PWD":/app -w /app "$IMAGE_NAME" mvn spring-boot:build-image -DskipTests

# Start application services
docker-compose --env-file .env up -d
