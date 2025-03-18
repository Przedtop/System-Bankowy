FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY ./target/bank.system-1.0.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]
