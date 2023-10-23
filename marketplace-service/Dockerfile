FROM openjdk:17
EXPOSE 8093
ADD target/marketplace-service-0.0.1-SNAPSHOT.jar marketplace-service-docker.jar
ENTRYPOINT ["java", "-jar", "marketplace-service-docker.jar"]
