FROM openjdk:11
EXPOSE 9001
COPY target/*.jar patient.jar
ADD target/*.jar  patient.jar
ENTRYPOINT ["java", "-jar", "patient.jar"]