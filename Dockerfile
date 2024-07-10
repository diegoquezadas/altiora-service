# Stage 1: Build Stage
FROM maven:3.9.0-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom files and source code
COPY api-gateway/pom.xml api-gateway/
COPY altiora-service-app/pom.xml altiora-service-app/
COPY altiora-service-app/pom.xml ./

# Download all dependencies (keep this step separated to take advantage of Docker cache)
RUN mvn -f api-gateway/pom.xml dependency:go-offline
RUN mvn -f altiora-service-app/pom.xml dependency:go-offline

# Copy the actual source code
COPY api-gateway/src api-gateway/src
COPY altiora-service-app/src altiora-service-app/src

# Build the api-gateway
RUN mvn -f api-gateway/pom.xml clean package -DskipTests

# Build the altiora-service-app
RUN mvn -f altiora-service-app/pom.xml clean package -DskipTests

# Stage 2: Run Stage
FROM eclipse-temurin:17-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the jar files from the build stage
COPY --from=build /app/api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar /app/api-gateway.jar
COPY --from=build /app/altiora-service-app/target/altiora-service-app-0.0.1-SNAPSHOT.jar /app/altiora-service-app.jar

# Expose the ports
EXPOSE 8080 9000

# Define environment variables for the applications
ENV API_GATEWAY_JAR=api-gateway.jar
ENV ALTIORA_SERVICE_APP_JAR=altiora-service-app.jar

# Run both applications
CMD java -jar $API_GATEWAY_JAR & java -jar $ALTIORA_SERVICE_APP_JAR
