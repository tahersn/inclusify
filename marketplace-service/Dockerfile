FROM openjdk:8
EXPOSE 8082
ADD target/marketplace-service-0.0.1-SNAPSHOT.jar marketplace-service-docker.jar
ENTRYPOINT ["java", "-jar", "marketplace-service-docker.jar"]