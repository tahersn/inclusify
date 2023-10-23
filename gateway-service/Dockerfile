FROM openjdk:17
EXPOSE 9999
ADD target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service-docker.jar
ENTRYPOINT ["java","-jar","gateway-service-docker.jar"]
