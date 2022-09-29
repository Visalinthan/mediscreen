FROM openjdk:11
ARG SERVICE_NAME=gateway
COPY target/*.jar gateway.jar
ADD target/*.jar  gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]