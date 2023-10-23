FROM openjdk:17
EXPOSE 8888
VOLUME /tmp
ADD target/config-service-0.0.1-SNAPSHOT.jar config-service-docker.jar
ENTRYPOINT ["java","-jar","config-service-docker.jar"]
