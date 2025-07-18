# Use a lightweight Java 17 image
FROM openjdk:24-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the built JAR file into the container
COPY build/libs/book-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Command to run your Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
