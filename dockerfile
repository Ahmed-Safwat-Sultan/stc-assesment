FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/files-manager-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that your Spring Boot application is running on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]


