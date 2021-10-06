FROM openjdk:11-jdk-slim-buster
COPY build/libs/login-service.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
