# Etap 1: Budowanie aplikacji (kompilacja kodu)
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

# Etap 2: Uruchomienie aplikacji w kontenerze
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Kopiowanie gotowego JAR-a do kontenera
COPY --from=builder /app/target/*.jar /app/app.jar

# Eksponowanie portu aplikacji
EXPOSE 8080

# Uruchamianie aplikacji
CMD ["java", "-jar", "/app/app.jar"]
