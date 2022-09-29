FROM openjdk:11
EXPOSE 8761
COPY target/*.jar server.jar
ADD target/*.jar server.jar
ENTRYPOINT ["java", "-jar", "server.jar"]