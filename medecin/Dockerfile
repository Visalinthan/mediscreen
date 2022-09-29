FROM openjdk:11
EXPOSE 9002
COPY target/*.jar medecin.jar
ADD target/*.jar  medecin.jar
ENTRYPOINT ["java", "-jar", "medecin.jar"]