FROM openjdk:17
EXPOSE 8092
ADD target/job-service-0.0.1-SNAPSHOT.jar job-service-docker.jar
ENTRYPOINT ["java","-jar","/job-service-docker.jar"]