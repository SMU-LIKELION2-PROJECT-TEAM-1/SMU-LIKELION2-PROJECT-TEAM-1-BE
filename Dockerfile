FROM openjdk:21-jdk

ARG JAR_FILE=build/libs/*SNAPSHOT.jar

COPY ${JAR_FILE} project.jar

ENTRYPOINT ["java", "-jar", "project.jar"]