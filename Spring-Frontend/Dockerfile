FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY target/frontend-1.0.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/app.jar"]
