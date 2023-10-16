FROM openjdk:17
EXPOSE 8091
ADD target/event-service-0.0.1-SNAPSHOT.jar event-service-docker.jar
ENTRYPOINT ["java", "-jar", "event-service-docker.jar"]