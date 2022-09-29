FROM openjdk:11
EXPOSE 9000
COPY target/*.jar client.jar
ADD target/*.jar  client.jar
ENTRYPOINT ["java", "-jar", "client.jar"]

