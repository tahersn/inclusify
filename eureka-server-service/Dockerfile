FROM openjdk:17
EXPOSE 8761
ADD target/eureka-server-service-0.0.1-SNAPSHOT.jar eureka-server-service-docker.jar
ENTRYPOINT ["java","-jar","/eureka-server-service-docker.jar"]
