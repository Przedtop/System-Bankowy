FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=builder /app/target/bank.system-1.0.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]
