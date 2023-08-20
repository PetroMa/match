FROM openjdk:8u121-jdk-alpine
ADD target/api-docker.jar api-docker.jar
ENTRYPOINT ["java", "-jar","api-docker.jar"]
EXPOSE 8080