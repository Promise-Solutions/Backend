FROM eclipse-temurin:21-jre

WORKDIR /app
COPY ./target/StudioZero-0.0.1-SNAPSHOT.jar studiozero-application.jar

ENTRYPOINT ["java", "-jar", "studiozero-application.jar"]
EXPOSE 5000