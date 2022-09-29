FROM openjdk:11
EXPOSE 9003
COPY target/*.jar consultation.jar
ADD target/*.jar  consultation.jar
ENTRYPOINT ["java", "-jar", "consultation.jar"]