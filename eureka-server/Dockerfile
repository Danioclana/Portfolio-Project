# 1. Primeira fase: construção do JAR
FROM maven:3.8.1-openjdk-17-slim AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Segunda fase: Criação da imagem final usando o JDK
FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
COPY --from=builder /app/target/*.jar /app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app.jar"]
